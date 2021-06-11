package com.example.recommendmeamovie.source.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val title: String,
    @ColumnInfo(name = "poster_path") val poster: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "movie_type") val movieType: String,
    val page: Int
)

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val question: String,
    val choices: Map<String, String>
)

@Entity(tableName = "remote_keys")
data class RemoteKey(@PrimaryKey val filter: String, val nextKey: Int?)