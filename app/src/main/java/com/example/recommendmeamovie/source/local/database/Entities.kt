package com.example.recommendmeamovie.source.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Long,
    val title: String,
    @ColumnInfo(name = "poster_path") val poster: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "movie_type") val movieType: String,
)

@Entity(tableName = "remote_keys")
data class RemoteKey(@PrimaryKey val filter: String, val nextKey: Int?)