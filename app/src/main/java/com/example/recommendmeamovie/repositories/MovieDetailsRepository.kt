package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.remote.MovieService
import com.example.recommendmeamovie.source.remote.asDomainModel

class MovieDetailsRepository {

    private val remoteDataSource = MovieService.retrofitService

    suspend fun getMovieDetails(id: Long): MovieDetails {
        return remoteDataSource.getMovieDetails(id, BuildConfig.API_KEY).asDomainModel()
    }


}