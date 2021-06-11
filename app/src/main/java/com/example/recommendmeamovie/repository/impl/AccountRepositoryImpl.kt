package com.example.recommendmeamovie.repository.impl

import com.example.recommendmeamovie.domain.Account
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.AccountRepository
import com.example.recommendmeamovie.source.local.database.MovieDatabase
import com.example.recommendmeamovie.source.local.datastore.AccountDataManager
import com.example.recommendmeamovie.source.local.datastore.SessionDataManager
import com.example.recommendmeamovie.source.remote.service.AccountApiService
import com.example.recommendmeamovie.util.Resource
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
    private val accountApiService: AccountApiService,
    private val movieDatabase: MovieDatabase,
    private val sessionDataManager: SessionDataManager,
    private val accountDataManager: AccountDataManager
): AccountRepository {
    override fun getAccount(): Flow<Resource<Account>> = networkBoundResource(
        query = {
            accountDataManager.getAccount()
        },
        fetch = {
            val sessionId = sessionDataManager.getSessionId().first()
            accountApiService.getAccountDetails(sessionId = sessionId)
        },
        saveFetchResult = {
            accountDataManager.setAccount(it.name, it.username, it.avatar.avatarContainer.avatarPath)
        }
    )

    override fun getWatchlist(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getYourFilms(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearSession() {
        sessionDataManager.clearSession()
    }


}