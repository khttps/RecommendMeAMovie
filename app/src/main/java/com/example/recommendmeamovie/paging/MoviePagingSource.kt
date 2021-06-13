package com.example.recommendmeamovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.asDomain
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val service: MovieApiService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: 1

        return try {
            val response = service.getSearchResults(
                query = query,
                page = position
            )

            LoadResult.Page(
                data = response.asDomain(),
                prevKey = if (position == 1) null else position.dec(),
                nextKey = if (response.results.isEmpty()) null else position.inc()
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.inc()
                ?: state.closestPageToPosition(it)?.nextKey?.dec()
        }
    }
}