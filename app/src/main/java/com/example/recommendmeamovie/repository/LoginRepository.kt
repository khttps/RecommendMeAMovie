package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Session
import com.example.recommendmeamovie.domain.Token
import com.example.recommendmeamovie.source.remote.NetworkToken
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun getToken() : Flow<Resource<Token>>

    fun getSessionId() : Flow<Resource<Session>>

}