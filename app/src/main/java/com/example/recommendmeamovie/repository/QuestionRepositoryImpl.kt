package com.example.recommendmeamovie.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.recommendmeamovie.domain.Question
import com.example.recommendmeamovie.source.local.QuestionDao
import com.example.recommendmeamovie.source.local.asQuestionDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val questionDao: QuestionDao
    ) : QuestionRepository {

    override fun getAllQuestions(): LiveData<List<Question>> {
        return questionDao.observeAllQuestions().map { it.asQuestionDomain() }
    }

}