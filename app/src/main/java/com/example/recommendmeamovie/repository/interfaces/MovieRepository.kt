package com.example.recommendmeamovie.repository.interfaces

import androidx.paging.PagingData
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val popularMoviesPaged: Flow<PagingData<Movie>>

    val topRatedMoviesPaged: Flow<PagingData<Movie>>

//    val watchlist: Flow<Resource<List<Movie>>>
//
//    val favorites: Flow<Resource<List<Movie>>>
//
//    val watchlistPaged: Flow<PagingData<Movie>>
//
//    val favoritesPaged: Flow<PagingData<Movie>>

    fun getPagedSearchResults(query: String) : Flow<PagingData<Movie>>
}