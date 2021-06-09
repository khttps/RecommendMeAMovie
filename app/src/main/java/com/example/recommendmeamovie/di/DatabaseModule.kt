package com.example.recommendmeamovie.di

import android.content.Context
import androidx.room.Room
import com.example.recommendmeamovie.source.local.MovieDatabase
import com.example.recommendmeamovie.source.local.dao.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideQuestionDao(movieDatabase: MovieDatabase) : QuestionDao {
        return movieDatabase.questionDao
    }





}