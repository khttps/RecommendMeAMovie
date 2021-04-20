package com.example.recommendmeamovie.source.remote
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

@JsonClass(generateAdapter = true)
data class NetworkMovieDetails(
    val id: Long,
    val title: String,
    val overview: String,
    val runtime: Int,
    val genres: List<Genre>?,
    val credits : NetworkCredits,
    @Json(name = "poster_path") val poster: String?,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Long,
    val name: String
)

@JsonClass(generateAdapter = true)
data class NetworkCredits(
    val cast: List<Cast>?,
    val crew: List<Crew>?
)

@JsonClass(generateAdapter = true)
data class Cast(
    val name : String,
    val character: String,
    @Json(name = "profile_path")  val picture : String?
)

@JsonClass(generateAdapter = true)
data class Crew(
    val name : String,
    val job: String,
    @Json(name = "profile_path") val picture : String?
)

