package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieService
import com.example.recommendmeamovie.source.remote.asDomainModel

class SearchResultsRepository {

    private val remoteDataSource = MovieService.retrofitService

    suspend fun getSearchResults(query: String): List<Movie> {
        return remoteDataSource.getSearchResults(query, BuildConfig.API_KEY).asDomainModel()
    }
}