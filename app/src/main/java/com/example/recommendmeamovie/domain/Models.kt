package com.example.recommendmeamovie.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Movie(
    val id : Long,
    val title : String,
    val poster : String?,
    val releaseDate : String?
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

data class Credit (
    val name : String,
    val role : String,
    val picture : String?
    )

data class Question (
    val questionText : String,
    val choices : Map<String, String>
)

data class Token (
    val success : Boolean,
    val requestToken : String
)

data class Account (
    val name : String,
    val username : String,
    val avatar: String
)