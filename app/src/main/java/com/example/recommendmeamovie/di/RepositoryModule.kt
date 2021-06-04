package com.example.recommendmeamovie.di

import com.example.recommendmeamovie.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun provideQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl) : QuestionRepository

    @Binds
    fun provideMovieRepository(movieRepository: MovieRepositoryImpl) : MovieRepository

    @Binds
    fun provideMovieDetailsRepository(movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl) : MovieDetailsRepository

    @Binds
    fun provideSearchResultsRepository(searchResultsRepositoryImpl: SearchRepositoryImpl) : SearchRepository
}