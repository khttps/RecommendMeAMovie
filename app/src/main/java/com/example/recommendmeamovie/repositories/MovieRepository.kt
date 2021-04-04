package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieService
import com.example.recommendmeamovie.source.remote.asDomainModel

private const val POPULAR_FILTER = "popular"
private const val TOP_RATED_FILTER = "top_rated"

class MovieRepository {

    private val remoteDataSource = MovieService.retrofitService

    suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource.getMovies(POPULAR_FILTER, BuildConfig.API_KEY).asDomainModel()
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        return remoteDataSource.getMovies(TOP_RATED_FILTER, BuildConfig.API_KEY).asDomainModel()
    }

    suspend fun getSearchResults(query: String): List<Movie> {
        return remoteDataSource.getSearchResults(query, BuildConfig.API_KEY).asDomainModel()
    }

}