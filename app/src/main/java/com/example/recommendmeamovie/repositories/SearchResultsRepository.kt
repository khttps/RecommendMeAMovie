package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.NetworkMovieMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchResultsRepository @Inject constructor(private val movieService : MovieApiService, private val networkMovieMapper: NetworkMovieMapper) {

    suspend fun getSearchResults(query: String): List<Movie>? {
        val searchResults = movieService.getSearchResults(query, BuildConfig.API_KEY)
        return networkMovieMapper.mapList(searchResults)
    }
}