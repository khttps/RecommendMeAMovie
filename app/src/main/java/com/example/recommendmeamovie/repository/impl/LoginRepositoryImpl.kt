package com.example.recommendmeamovie.repository.impl

import com.example.recommendmeamovie.repository.LoginRepository
import com.example.recommendmeamovie.source.datastore.SessionDataManager
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import com.example.recommendmeamovie.util.networkBoundResource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dataStore: SessionDataManager
) : LoginRepository {

    override fun getToken() = networkBoundResource(
        query = {
            dataStore.getToken()
        },
        fetch = {
            movieApiService.getToken().also {
                if (!it.success)
                    throw Throwable(message = "Couldn't retrieve token. ")
            }
        },
        saveFetchResult = {
            dataStore.setToken(it.requestToken)
        }
    )
}