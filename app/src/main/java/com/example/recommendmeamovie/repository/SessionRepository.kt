package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    val loginStatus: Flow<Boolean>

    fun getSessionId(): Flow<Resource<String>>

    suspend fun clearSession()

}