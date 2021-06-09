package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun getToken(): Flow<Resource<String>>
}