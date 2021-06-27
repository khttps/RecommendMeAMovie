package com.example.recommendmeamovie.source.remote.service

import com.example.recommendmeamovie.BuildConfig.API_KEY
import com.example.recommendmeamovie.source.remote.dto.MoviesContainer
import com.example.recommendmeamovie.source.remote.dto.NetworkMovieDetails
import com.example.recommendmeamovie.source.remote.dto.NetworkToken
import retrofit2.http.*

interface MovieApiService {

    @GET("3/search/movie")
    suspend fun getSearchResults(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
    ): MoviesContainer

    @GET("3/movie/{filter}")
    suspend fun getMovies(
        @Path("filter") filter: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
    ): MoviesContainer

    @GET("3/account/{account_id}/{list_type}/movies")
    suspend fun getAccountMovieList(
        @Path("account_id") accountId: Long,
        @Path("list_type") type: String,
        @Query("sessionId") sessionId: String,
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "created_at.desc",
        @Query("api_key") apiKey: String = API_KEY,
    ): MoviesContainer

    @GET("3/discover/movie")
    suspend fun getMovieRecommendation(
        @Query("primary_release_date.gte") releasedAfter: String,
        @Query("primary_release_date.lte") releasedBefore: String,
        @Query("vote_average.gte") voteGreaterThan: Int,
        @Query("vote_average.lte") voteLessThan: Int,
        @Query("with_genres") genre: Long,
        @Query("api_key") apiKey: String = API_KEY
    ): MoviesContainer

    @GET("3/movie/{id}?append_to_response=credits,account_states")
    suspend fun getMovieDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String?,
    ): NetworkMovieDetails

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
}

