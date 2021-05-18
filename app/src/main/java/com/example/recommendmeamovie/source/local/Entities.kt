package com.example.recommendmeamovie.source.local

import androidx.room.*

@Entity(indices=[Index(value = ["title", "poster_path", "release_date", "movie_type"])],tableName = "movies")
data class MovieEntity (
    @PrimaryKey(autoGenerate = false) val id: Long,
    val title: String,
    @ColumnInfo(name = "poster_path") val poster: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "movie_type") val movieType: String
)

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val question : String,
    val choices : Map<String, String>
    )