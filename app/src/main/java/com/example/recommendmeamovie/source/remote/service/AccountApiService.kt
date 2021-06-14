package com.example.recommendmeamovie.source.remote.service

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.BuildConfig.API_KEY
import com.example.recommendmeamovie.source.remote.dto.*
import okhttp3.RequestBody
import retrofit2.http.*

interface AccountApiService {

    @GET("3/authentication/token/new")
    suspend fun getToken(
        @Query("api_key") apiKey: String = API_KEY,
    ): NetworkToken

    @FormUrlEncoded
    @POST("3/authentication/session/new")
    suspend fun createSession(
        @Query("api_key") apiKey: String = API_KEY,
        @Field("request_token") token: String
    ): NetworkSession

    @FormUrlEncoded
    @POST("3/authentication/session/new")
    suspend fun addOrRemoveMovieWatchlist(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String,
        @Field("media_type") type: String = "movie",
        @Field("media_id") movieId: Long,
        @Field("watchlist") addToWatchlist: Boolean
    )

    @FormUrlEncoded
    @POST("3/authentication/session/new")
    suspend fun addOrRemoveMovieFavorites(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String,
        @Field("media_type") type: String = "movie",
        @Field("media_id") movieId: Long,
        @Field("favorites") addToFavorites: Boolean
    )

    @GET("3/account")
    suspend fun getAccount(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String
    ): NetworkAccount

    @HTTP(method = "DELETE", path = "3/authentication/session", hasBody = true)
    suspend fun deleteSession(
        @Query("api_key") apiKey: String = API_KEY,
        @Body session: RequestBody
    )

}