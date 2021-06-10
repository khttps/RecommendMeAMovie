package com.example.recommendmeamovie.di

import android.content.Context
import androidx.room.Room
import com.example.recommendmeamovie.source.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test-db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context,
            MovieDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()



}