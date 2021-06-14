package com.example.recommendmeamovie.repository.interfaces

import com.example.recommendmeamovie.domain.Account
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAccount(): Flow<Resource<Account>>

    suspend fun clearAccount()
}