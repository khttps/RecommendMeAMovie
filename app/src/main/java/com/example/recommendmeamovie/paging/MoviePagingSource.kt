package com.example.recommendmeamovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.asDomain
import com.example.recommendmeamovie.source.remote.service.MovieApiService

class MoviePagingSource(
    private val movieApiService: MovieApiService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: 1

        return try {
            val response = movieApiService.getSearchResults(
                query = query,
                page = position
            )

            val domainList = response.asDomain()

            LoadResult.Page(
                domainList,
                if (position == 1) null else position.dec(),
                if (domainList.isEmpty()) null else position.inc()
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}