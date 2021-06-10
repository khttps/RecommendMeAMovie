package com.example.recommendmeamovie.source.local.datastore

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.sessionDataStore by dataStore(
    fileName = "session",
    serializer = SessionSerializer
)

@Singleton
class SessionDataManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.sessionDataStore

    suspend fun setSessionId(sessionId: String) {
        dataStore.updateData {
            it.toBuilder()
                .clearRequestToken()
                .setSessionId(sessionId)
                .setIsSignedIn(true)
                .build()
        }
    }

    fun getSessionId(): Flow<String> = dataStore.data.map {
        it.sessionId
    }

    suspend fun setToken(token: String) {
        dataStore.updateData {
            it.toBuilder()
                .setRequestToken(token)
                .build()
        }
    }

    fun getToken(): Flow<String> = dataStore.data.map {
        it.requestToken
    }

    fun getLoginStatus() = dataStore.data.map {
        it.isSignedIn
    }

    suspend fun clearSession() {
        dataStore.updateData {
            it.toBuilder()
                .clearSessionId()
                .setIsSignedIn(false)
                .build()
        }
    }
}