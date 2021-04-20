package com.example.recommendmeamovie.source.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("3/search/movie")
    suspend fun getSearchResults(@Query("query") query: String, @Query("api_key") key : String): MoviesContainer

    @GET("3/movie/{filter}")
    suspend fun getMovies(
        @Path("filter") filter: String,
        @Query("api_key") key: String
    ): MoviesContainer

    @GET("3/discover/movie")
    suspend fun getMovieRecommendation(
        @Query("api_key") key: String,
        @Query("primary_release_date.gte") releasedAfter: String,
        @Query("primary_release_date.lte") releasedBefore: String,
        @Query("vote_average.gte") voteGreaterThan: Int,
        @Query("vote_average.lte") voteLessThan: Int,
        @Query("with_genres") genre: Long
    ): MoviesContainer

    @GET("3/movie/{id}?append_to_response=credits")
    suspend fun getMovieDetails(@Path("id") id: Long, @Query("api_key") key: String): NetworkMovieDetails

}

