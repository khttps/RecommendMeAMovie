package com.example.recommendmeamovie.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recommendmeamovie.source.local.database.dao.MovieDao
import com.example.recommendmeamovie.source.local.database.dao.QuestionDao
import com.example.recommendmeamovie.source.local.database.dao.RemoteKeyDao

@Database(entities = [MovieEntity::class, QuestionEntity::class, RemoteKey::class], version = 4, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
    abstract val questionDao : QuestionDao
    abstract val remoteKeyDao: RemoteKeyDao

    companion object {
        const val DATABASE_NAME = "movie-db"
    }

}