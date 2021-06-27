package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.repository.interfaces.AccountRepository
import com.example.recommendmeamovie.source.local.datastore.AccountManager
import com.example.recommendmeamovie.source.local.datastore.SessionManager
import com.example.recommendmeamovie.source.remote.service.AccountApiService
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val service: AccountApiService,
    private val accountManager: AccountManager,
    private val sessionManager: SessionManager
): AccountRepository {
    override fun getAccount() = networkBoundResource(
        query = {
            accountManager.getAccount()
        },
        fetch = {
            sessionManager.getSessionId().first().let { sessionId ->
                if (sessionId.isEmpty())
                    throw Throwable(message = "User is not signed in. ")

                service.getAccount(sessionId = sessionId)
            }
        },
        saveFetchResult = {
            accountManager.setAccount(
                it.id,
                it.name,
                it.username,
                it.avatar.avatarContainer.avatarPath
            )
        }
    )

    override suspend fun clearAccount() {
        accountManager.clearAccount()
    }
}