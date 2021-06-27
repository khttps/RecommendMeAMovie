package com.example.recommendmeamovie.repository.interfaces

import androidx.paging.PagingData
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val popularMoviesPaged: Flow<PagingData<Movie>>

    val topRatedMoviesPaged: Flow<PagingData<Movie>>

    fun getPagedSearchResults(query: String): Flow<PagingData<Movie>>

    fun getWatchlistPaged(accountId: Long, sessionId: String): Flow<PagingData<Movie>>

    fun getFavoritesPaged(accountId: Long, sessionId: String): Flow<PagingData<Movie>>

    fun getUserFavorites(): Flow<Resource<List<Movie>>>

    fun getUserWatchlist(): Flow<Resource<List<Movie>>>

    suspend fun clearUserCachedMovies()
}