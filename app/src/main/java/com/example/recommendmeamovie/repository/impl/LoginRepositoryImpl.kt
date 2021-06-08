package com.example.recommendmeamovie.repository.impl

import com.example.recommendmeamovie.repository.LoginRepository
import com.example.recommendmeamovie.source.datastore.DataStoreManager
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.util.networkBoundResource
import timber.log.Timber
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dataStore: DataStoreManager
) : LoginRepository {

    override fun getToken() = networkBoundResource(
        query = {
            dataStore.getToken()
        },
        fetch = {
            movieApiService.getToken().also {
                if (!it.success)
                    throw Throwable(it.statusMessage ?: "Couldn't retrieve token.")
            }
        },
        saveFetchResult = {
            dataStore.setToken(it.requestToken!!)
        }
    )
}