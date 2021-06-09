package com.example.recommendmeamovie.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesContainer(
    val results: List<NetworkMovie>,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    val id: Long,
    val title: String,
    @Json(name = "poster_path") val poster: String?,
    @Json(name = "release_date") val releaseDate: String?
)
