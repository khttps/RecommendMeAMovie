package com.example.recommendmeamovie.repository.interfaces

import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    fun getMovieDetails(id: Long, sessionId: String?): Flow<Resource<MovieDetails>>

}