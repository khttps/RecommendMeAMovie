package com.example.recommendmeamovie.source.remote.service

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.source.remote.dto.NetworkAccount
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountApiService {

    @GET("3/account")
    suspend fun getAccountDetails(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("session_id") sessionId: String
    ): NetworkAccount

}