package com.example.recommendmeamovie.domain

import com.example.recommendmeamovie.source.remote.Genre

data class Movie(
    val id : Long,
    val title : String,
    val poster : String,
    val releaseDate : String
)

data class MovieDetails(
    val id: Long,
    val title: String,
    val overview: String,
    val runtime: Int,
    val genres: List<Genre>?,
    val cast: List<Credit>?,
    val crew: List<Credit>?,
    val poster: String,
    val releaseDate: String,
    val voteAverage: Double
)

data class Credit (
    val name : String,
    val role : String,
    val picture : String?
    )