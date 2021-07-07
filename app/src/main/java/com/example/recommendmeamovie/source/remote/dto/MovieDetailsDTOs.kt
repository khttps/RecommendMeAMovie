package com.example.recommendmeamovie.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMovieDetails(
    val id: Long,
    val title: String,
    val overview: String,
    val runtime: Int,
    val genres: List<Genre>?,
    val credits: NetworkCredits,
    @Json(name = "poster_path") val poster: String?,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "account_states") val accountStates: AccountStates?
)

@JsonClass(generateAdapter = true)
data class NetworkCredits(
    val cast: List<Cast>?,
    val crew: List<Crew>?
)

@JsonClass(generateAdapter = true)
data class Cast(
    val name: String,
    val character: String,
    @Json(name = "profile_path") val picture: String?
)

@JsonClass(generateAdapter = true)
data class Crew(
    val name: String,
    val job: String,
    @Json(name = "profile_path") val picture: String?
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Long,
    val name: String
)

data class AccountStates(
    val favorite: Boolean,
    val rated: Boolean,
    val watchlist: Boolean
)

