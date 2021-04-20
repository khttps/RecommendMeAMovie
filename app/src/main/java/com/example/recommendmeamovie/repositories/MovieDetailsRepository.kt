package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomain
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieDetailsRepository
@Inject constructor(
    private val movieService : MovieApiService) {

    suspend fun getMovieDetails(id: Long): MovieDetails {
        val movieDetails =  movieService.getMovieDetails(id, BuildConfig.API_KEY)
        return movieDetails.asDomain()
    }


}