package com.example.recommendmeamovie.domain

data class Movie(
    val id: Long,
    val title: String,
    val poster: String?,
    val releaseDate: String?
)

data class MovieDetails(
    val id: Long,
    val title: String,
    val overview: String,
    val runtime: Int,
    val genres: String?,
    val cast: List<Credit>?,
    val crew: List<Credit>?,
    val poster: String?,
    val releaseDate: String,
    val voteAverage: Double
)

data class Credit(
    val name: String,
    val role: String,
    val picture: String?
)

data class Account(
    val id: Long,
    val name: String,
    val username: String,
    val avatar: String
)