package com.example.recommendmeamovie.repository.impl

import com.example.recommendmeamovie.repository.SessionRepository
import com.example.recommendmeamovie.source.datastore.DataStoreManager
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dataStore: DataStoreManager
): SessionRepository {

    override val loginStatus = dataStore.getLoginStatus()

    override fun getSessionId() = networkBoundResource(
        query = {
            dataStore.getSessionId()
        },
        fetch = {
            val token = dataStore.getToken().first()
            movieApiService.createSession(token = token).also {
                if (!it.success)
                    throw Throwable(message = it.sessionId)
            }
        },
        saveFetchResult = {
            dataStore.setSessionId(it.sessionId)
        },
        shouldFetch = {
            it.isEmpty()
        }
    )

    override suspend fun clearSession() = dataStore.clearSession()


}