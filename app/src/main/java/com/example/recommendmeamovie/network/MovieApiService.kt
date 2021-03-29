package com.example.recommendmeamovie.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface MovieApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") key : String) : String

    @GET("3/discover/movie")
    suspend fun getMovieRecommendation(
        @Query("api_key") key : String,
        @Query("primary_release_date.gte") releasedAfter : String,
        @Query("primary_release_date.lte") releasedBefore : String,
        @Query("vote_average.gte") voteGreaterThan : Int,
        @Query("vote_average.lte") voteLessThan : Int,
        @Query("with_genres") genre : Long
    ) : List<Movie>

    @GET("3/movie/{id}")
    suspend fun getMovieDetails(@Path("id") id : Long, @Query("api_key") key: String) : MovieDetails

    @GET("3/movie/{id}/credits")
    suspend fun getMovieCredits(@Path("id") id : Long, @Query("api_key") key: String) : Credits

}

object MovieService {
    val retrofitService: MovieApiService = retrofit.create(MovieApiService::class.java)
}

