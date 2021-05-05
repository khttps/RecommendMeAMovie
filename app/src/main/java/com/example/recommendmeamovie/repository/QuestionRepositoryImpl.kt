package com.example.recommendmeamovie.repository

import android.content.Context
import com.example.recommendmeamovie.domain.Question
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
    ) : QuestionRepository {

    override suspend fun getQuestions() = listOf<Question>()

}