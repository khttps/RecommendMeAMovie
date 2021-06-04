package com.example.recommendmeamovie.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recommendmeamovie.domain.Question

@Database(entities = [MovieEntity::class, QuestionEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao : MovieDao
    abstract val questionDao : QuestionDao

    companion object {
        const val DATABASE_NAME = "movie-db"
    }

}