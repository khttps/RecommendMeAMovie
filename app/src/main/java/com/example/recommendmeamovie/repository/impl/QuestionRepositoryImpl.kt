package com.example.recommendmeamovie.repository.impl

import androidx.lifecycle.LiveData
import com.example.recommendmeamovie.domain.Question
import com.example.recommendmeamovie.repository.QuestionRepository
import com.example.recommendmeamovie.source.local.QuestionDao
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class QuestionRepositoryImpl @Inject constructor(
    private val questionDao: QuestionDao
    ) : QuestionRepository {

    override fun getAllQuestions(): LiveData<List<Question>> {
        TODO("Not yet implemented")
    }

}