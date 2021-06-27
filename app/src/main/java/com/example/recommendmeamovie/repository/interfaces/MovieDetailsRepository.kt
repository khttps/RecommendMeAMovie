package com.example.recommendmeamovie.repository.interfaces

import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    fun getMovieDetails(id: Long, sessionId: String?): Flow<Resource<MovieDetails>>

    suspend fun setWatchlist(id: Long, sessionId: String, addToWatchlist: Boolean)

    suspend fun setFavorite(id: Long, sessionId: String, addToFavorite: Boolean)

}