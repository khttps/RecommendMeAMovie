package com.example.recommendmeamovie.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import javax.inject.Inject


@Database (entities = [MovieEntity::class/*, MovieDetailsEntity::class, CreditEntity::class*/], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "movie-db"
    }

    abstract val movieDao : MovieDao

}