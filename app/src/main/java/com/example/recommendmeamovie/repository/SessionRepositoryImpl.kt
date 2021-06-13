package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.repository.interfaces.SessionRepository
import com.example.recommendmeamovie.source.local.datastore.SessionDataManager
import com.example.recommendmeamovie.source.remote.service.AccountApiService
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val service: AccountApiService,
    private val dataStore: SessionDataManager
): SessionRepository {

    override fun getRequestToken() = networkBoundResource(
        query = {
            dataStore.getToken()
        },
        fetch = {
            service.getToken().also {
                if (!it.success)
                    throw Throwable(message = "Couldn't retrieve token. ")
            }
        },
        saveFetchResult = {
            dataStore.setToken(it.requestToken)
        }
    )

    override fun getSessionId() = networkBoundResource(
        query = {
            dataStore.getSessionId()
        },
        fetch = {
            val token = dataStore.getToken().first()
            service.createSession(token = token).also {
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

    override suspend fun clearSession() {
        dataStore.clearSession()
    }


}