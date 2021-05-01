package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomain
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class SearchResultsRepository
@Inject constructor(private val movieService : MovieApiService) {

    suspend fun getSearchResults(query: String): Flow<List<Movie>> = flow {
        val searchResults = movieService.getSearchResults(query, BuildConfig.API_KEY)
        emit(searchResults.asDomain())
    }
}