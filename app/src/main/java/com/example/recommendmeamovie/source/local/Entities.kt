package com.example.recommendmeamovie.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "movies")
data class MovieEntity (
    @PrimaryKey (autoGenerate = false) val id: Long,
    val title: String,
    @ColumnInfo(name = "poster_path") val poster: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "movie_type") val movieType: MovieType
)

//@Entity (tableName = "movies_details")
//data class MovieDetailsEntity(
//    @PrimaryKey (autoGenerate = false) val id: Long,
//    val title: String,
//    val overview: String,
//    val runtime: Int,
//    val genres: String?,
//    val cast: List<CreditEntity>?,
//    val crew: List<CreditEntity>?,
//    @ColumnInfo(name = "poster_path") val poster: String?,
//    @ColumnInfo(name = "release_date") val releaseDate: String,
//    @ColumnInfo(name = "vote_average") val voteAverage: Double
//)
//
//@Entity (tableName = "credits")
//data class CreditEntity (
//    @PrimaryKey (autoGenerate = true) val id : Long,
//    val name : String,
//    val role : String,
//    val picture : String?
//)
