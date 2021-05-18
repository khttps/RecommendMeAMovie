package com.example.recommendmeamovie.source.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {

    @Query ("SELECT * FROM questions")
    fun observeAllQuestions() : LiveData<List<QuestionEntity>>

    @Query ("SELECT * FROM questions")
    fun getQuestions() : List<QuestionEntity>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(questionEntity: QuestionEntity)

    @Delete
    suspend fun deleteQuestion(questionEntity: QuestionEntity)

}