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
import com.example.recommendmeamovie.source.remote.service.MovieApiService

@ExperimentalPagingApi
class MovieRemoteMediator(
    private val filter: String,
    private val movieApiService: MovieApiService,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, MovieEntity>() {

    private val movieDao = movieDatabase.movieDao
    private val remoteKeyDao = movieDatabase.remoteKeyDao

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = movieDatabase.withTransaction {
                        remoteKeyDao.remoteKeyByQuery(filter = filter)
                    }

                    if (remoteKey.nextKey == null)
                        return MediatorResult.Success(endOfPaginationReached = true)

                    remoteKey.nextKey
                }
            }

            val response = movieApiService.getMovies(filter = filter, page = loadKey)

            movieDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery(filter)
                    movieDao.deleteAll(filter)
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(filter, response.page + 1)
                )
                movieDao.insertAll(response.asEntity(filter))
            }

            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())

        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

}