package com.example.recommendmeamovie.repository.impl

import com.example.recommendmeamovie.repository.SessionRepository
import com.example.recommendmeamovie.source.datastore.SessionDataManager
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dataStore: SessionDataManager
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
                    throw Throwable(message = "Couldn't retrieve session. ")
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