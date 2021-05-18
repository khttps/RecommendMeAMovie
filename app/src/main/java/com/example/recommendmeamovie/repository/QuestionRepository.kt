package com.example.recommendmeamovie.repository

import androidx.lifecycle.LiveData
import com.example.recommendmeamovie.domain.Question

interface QuestionRepository {
    fun getAllQuestions() : LiveData<List<Question>>
}