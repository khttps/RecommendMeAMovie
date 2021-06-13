package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.repository.interfaces.AccountRepository
import com.example.recommendmeamovie.source.local.datastore.AccountDataManager
import com.example.recommendmeamovie.source.local.datastore.SessionDataManager
import com.example.recommendmeamovie.source.remote.service.AccountApiService
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val service: AccountApiService,
    private val sessionDataStore: SessionDataManager,
    private val accountDataStore: AccountDataManager
): AccountRepository {
    override fun getAccount() = networkBoundResource(
        query = {
            accountDataStore.getAccount()
        },
        fetch = {
            val sessionId = sessionDataStore.getSessionId().first()
            service.getAccount(sessionId = sessionId)
        },
        saveFetchResult = {
            accountDataStore.setAccount(
                it.id,
                it.name,
                it.username,
                it.avatar.avatarContainer.avatarPath
            )
        }
    )

    override suspend fun clearAccount() {
        accountDataStore.clearAccount()
        sessionDataStore.clearSession()
    }
}