package com.example.recommendmeamovie.di

import androidx.paging.ExperimentalPagingApi
import com.example.recommendmeamovie.repository.*
import com.example.recommendmeamovie.repository.interfaces.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    @ExperimentalPagingApi
    fun provideMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    fun provideMovieDetailsRepository(movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl): MovieDetailsRepository

    @Binds
    fun provideSessionRepository(sessionRepository: SessionRepositoryImpl): SessionRepository

    @Binds
    fun provideAccountRepository(accountRepository: AccountRepositoryImpl): AccountRepository
}