package com.example.recommendmeamovie.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recommendmeamovie.source.local.database.dao.MovieDao
import com.example.recommendmeamovie.source.local.database.dao.RemoteKeyDao

@Database(entities = [MovieEntity::class, RemoteKey::class], version = 5, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
    abstract val remoteKeyDao: RemoteKeyDao

    companion object {
        const val DATABASE_NAME = "movie-db"
    }

}