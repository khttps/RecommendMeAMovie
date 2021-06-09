package com.example.recommendmeamovie.source.datastore

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.accountDataStore by dataStore(
    fileName = "account",
    serializer = SessionSerializer
)

@Singleton
class AccountDataManager @Inject constructor(@ApplicationContext context: Context) {

    val dataStore = context.accountDataStore

    fun getAccount() = dataStore



}