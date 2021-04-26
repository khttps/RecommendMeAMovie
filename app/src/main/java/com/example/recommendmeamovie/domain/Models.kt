package com.example.recommendmeamovie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Movie(
    val id : Long,
    val title : String,
    val poster : String?,
    val releaseDate : String?
)

@Parcelize
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
) : Parcelable

@Parcelize
data class Credit (
    val name : String,
    val role : String,
    val picture : String?
    ) : Parcelable