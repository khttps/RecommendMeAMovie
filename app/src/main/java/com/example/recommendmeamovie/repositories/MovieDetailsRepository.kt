package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomainModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieDetailsRepository @Inject constructor(private val remoteDataSource : MovieApiService) {

    suspend fun getMovieDetails(id: Long): MovieDetails {
        return remoteDataSource.getMovieDetails(id, BuildConfig.API_KEY).asDomainModel()
    }


}