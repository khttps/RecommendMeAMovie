package com.example.recommendmeamovie.source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query ("SELECT * FROM questions")
    fun getQuestions() : Flow<List<QuestionEntity>>

}