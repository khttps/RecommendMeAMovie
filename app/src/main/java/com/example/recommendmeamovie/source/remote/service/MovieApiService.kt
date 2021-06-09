package com.example.recommendmeamovie.source.remote.service

import com.example.recommendmeamovie.BuildConfig.API_KEY
import com.example.recommendmeamovie.source.remote.dto.MoviesContainer
import com.example.recommendmeamovie.source.remote.dto.NetworkMovieDetails
import com.example.recommendmeamovie.source.remote.dto.NetworkSession
import com.example.recommendmeamovie.source.remote.dto.NetworkToken
import retrofit2.http.*

interface MovieApiService {



    @GET("3/search/movie")
    suspend fun getSearchResults(
        @Query("query") query: String,
        @Query("api_key") key : String = API_KEY
    ): MoviesContainer

    @GET("3/movie/{filter}")
    suspend fun getMovies(
        @Path("filter") filter: String,
        @Query("api_key") key: String = API_KEY
    ): MoviesContainer

    @GET("3/discover/movie")
    suspend fun getMovieRecommendation(
        @Query("api_key") key: String = API_KEY,
        @Query("primary_release_date.gte") releasedAfter: String,
        @Query("primary_release_date.lte") releasedBefore: String,
        @Query("vote_average.gte") voteGreaterThan: Int,
        @Query("vote_average.lte") voteLessThan: Int,
        @Query("with_genres") genre: Long
    ): MoviesContainer

    @GET("3/movie/{id}?append_to_response=credits")
    suspend fun getMovieDetails(
        @Path("id") id: Long,
        @Query("api_key") key: String = API_KEY
    ): NetworkMovieDetails

    @GET("3/authentication/token/new")
    suspend fun getToken(
        @Query("api_key") key: String = API_KEY,
    ): NetworkToken

    @FormUrlEncoded
    @POST("3/authentication/session/new")
    suspend fun createSession(
        @Query("api_key") key: String = API_KEY,
        @Field("request_token") token: String
    ): NetworkSession

}

