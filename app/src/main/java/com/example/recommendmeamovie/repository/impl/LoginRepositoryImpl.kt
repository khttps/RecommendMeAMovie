package com.example.recommendmeamovie.repository.impl

import com.example.recommendmeamovie.domain.Session
import com.example.recommendmeamovie.domain.Token
import com.example.recommendmeamovie.repository.LoginRepository
import com.example.recommendmeamovie.source.datastore.DataStoreManager
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dataStore: DataStoreManager
) : LoginRepository {
    override fun getToken(): Flow<Resource<Token>> {
        TODO("Not yet implemented")
    }

    override fun getSessionId(): Flow<Resource<Session>> {
        TODO("Not yet implemented")
    }
}