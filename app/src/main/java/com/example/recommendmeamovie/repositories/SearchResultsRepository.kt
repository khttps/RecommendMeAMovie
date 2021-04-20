package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomainModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchResultsRepository @Inject constructor(private val remoteDataSource : MovieApiService) {

    suspend fun getSearchResults(query: String): List<Movie> {
        return remoteDataSource.getSearchResults(query, BuildConfig.API_KEY).asDomainModel()
    }
}