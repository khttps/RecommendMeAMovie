package com.example.recommendmeamovie.source.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database (entities = [MovieEntity::class/*, MovieDetailsEntity::class, CreditEntity::class*/], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao : MovieDao

}