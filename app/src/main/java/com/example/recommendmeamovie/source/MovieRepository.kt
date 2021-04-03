package com.example.recommendmeamovie.source

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.remote.MovieService
import com.example.recommendmeamovie.source.remote.asDomainModel

const val API_KEY = BuildConfig.API_KEY
const val POPULAR_FILTER = "popular"
const val TOP_RATED_FILTER = "top_rated"


class MovieRepository {

    private val remoteDataSource = MovieService.retrofitService

    suspend fun getPopularMovies() : List<Movie> {
        return remoteDataSource.getMovies(POPULAR_FILTER, API_KEY).asDomainModel()
    }

    suspend fun getTopRatedMovies() : List<Movie> {
        return remoteDataSource.getMovies(TOP_RATED_FILTER, API_KEY).asDomainModel()
    }

    suspend fun getMovieDetails(id : Long) : MovieDetails{
        return remoteDataSource.getMovieDetails(id, API_KEY).asDomainModel()
    }


}