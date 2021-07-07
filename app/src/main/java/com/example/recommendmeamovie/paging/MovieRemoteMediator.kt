package com.example.recommendmeamovie.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.recommendmeamovie.source.local.database.MovieDatabase
import com.example.recommendmeamovie.source.local.database.MovieEntity
import com.example.recommendmeamovie.source.local.database.RemoteKey
import com.example.recommendmeamovie.source.remote.asEntity
import com.example.recommendmeamovie.source.remote.dto.MoviesContainer
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class MovieRemoteMediator(
    private val filter: String,
    private val request: suspend (page: Int) -> MoviesContainer,
    private val database: MovieDatabase
) : RemoteMediator<Int, MovieEntity>() {

    private val movieDao = database.movieDao
    private val remoteKeyDao = database.remoteKeyDao

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = remoteKeyDao.remoteKeyByFilter(filter = filter)

                    if (remoteKey.nextKey == null)
                        return MediatorResult.Success(endOfPaginationReached = true)

                    remoteKey.nextKey
                }
            }

            val response = request(loadKey)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByFilter(filter)
                    movieDao.deleteAll(filter)
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(filter, response.page.inc())
                )
                movieDao.insertAll(response.asEntity(filter))
            }
            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

}