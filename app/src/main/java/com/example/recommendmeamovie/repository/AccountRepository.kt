package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Account
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAccount(): Flow<Resource<Account>>

    fun getWatchlist(): Flow<List<Movie>>

    fun getYourFilms(): Flow<List<Movie>>

    suspend fun clearSession()
}