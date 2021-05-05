package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Question

interface QuestionRepository {

    suspend fun getQuestions() : List<Question>
}