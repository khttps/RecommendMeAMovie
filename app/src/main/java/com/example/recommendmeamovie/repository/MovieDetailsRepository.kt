package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomain
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class MovieDetailsRepository
@Inject constructor(
    private val movieService : MovieApiService) {

    fun getMovieDetails(id: Long) : Flow<MovieDetails> = flow {
        val movieDetails =  movieService.getMovieDetails(id)
        emit(movieDetails.asDomain())
    }


}