package com.example.recommendmeamovie.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface MovieApiService {

    @GET("3/movie/popular")
    fun getPopularMovies(@Query("api_key") key : String) : Call<String>

}

object MovieService {
    val retrofitService: MovieApiService = retrofit.create(MovieApiService::class.java)
}

