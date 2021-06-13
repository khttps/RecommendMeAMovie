package com.example.recommendmeamovie.repository.interfaces

import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    fun getRequestToken(): Flow<Resource<String>>

    fun getSessionId(): Flow<Resource<String>>

    suspend fun clearSession()

}