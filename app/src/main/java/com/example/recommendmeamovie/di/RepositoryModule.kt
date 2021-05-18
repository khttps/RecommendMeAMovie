package com.example.recommendmeamovie.di

import com.example.recommendmeamovie.repository.QuestionRepository
import com.example.recommendmeamovie.repository.QuestionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl) : QuestionRepository
}