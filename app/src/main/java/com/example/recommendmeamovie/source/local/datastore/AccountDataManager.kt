package com.example.recommendmeamovie.source.local.datastore

import android.content.Context
import androidx.datastore.dataStore
import com.example.recommendmeamovie.domain.Account
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.accountDataStore by dataStore(
    fileName = "account",
    serializer = AccountSerializer
)

@Singleton
class AccountDataManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.accountDataStore

    fun getAccount() = dataStore.data.map {
        Account(
            it.id,
            it.name,
            it.username,
            it.avatar
        )
    }

    suspend fun setAccount(id: Long, name: String, username: String, avatar: String) {
        dataStore.updateData {
            it.toBuilder()
                .setId(id)
                .setName(name)
                .setUsername(username)
                .setAvatar(avatar)
                .build()
        }
    }

    suspend fun clearAccount() {
        dataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}