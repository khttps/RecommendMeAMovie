package com.example.recommendmeamovie.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieContainer(
    val results: List<Movie>,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int,
    val title: String,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "release_date") val releaseDate: String,
)

@JsonClass(generateAdapter = true)
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val runtime: Int,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "genre_ids") val genres: List<Genre>?
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Long,
    val name: String
)

@JsonClass(generateAdapter = true)
data class Credits(
    val cast: List<Cast>?,
    val crew: List<Crew>?
)

@JsonClass(generateAdapter = true)
data class Cast(
    val name : String,
    val character: String,
    @Json(name = "profile_path")  val picture : String
)

@JsonClass(generateAdapter = true)
data class Crew(
    val name : String,
    val job: String,
    @Json(name = "profile_path") val picture : String
)



//enum class Genre(val id : Int) {
//    ACTION (28),
//    ANIMATION (16),
//    COMEDY (35),
//    CRIME (80),
//    DRAMA (18),
//    FANTASY(14),
//    HORROR(27),
//    MYSTERY(9648),
//    ROMANCE(10749),
//    SCIENCE_FICTION(878),
//    THRILLER(53),
//    WAR(10752),
//    WESTERN(37)
//}