package com.example.recommendmeamovie.di

import androidx.paging.ExperimentalPagingApi
import com.example.recommendmeamovie.repository.*
import com.example.recommendmeamovie.repository.impl.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun provideQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl): QuestionRepository

    @Binds
    @ExperimentalPagingApi
    fun provideMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    fun provideMovieDetailsRepository(movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl): MovieDetailsRepository

    @Binds
    fun provideSearchRepository(searchResultsRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    fun provideLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

    @Binds
    fun provideSessionRepository(sessionRepository: SessionRepositoryImpl): SessionRepository

    @Binds
    fun provideAccountRepository(accountRepository: AccountRepositoryImpl): AccountRepository
}