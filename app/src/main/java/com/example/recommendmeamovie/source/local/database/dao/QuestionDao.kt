package com.example.recommendmeamovie.source.local.database.dao

import androidx.room.*
import com.example.recommendmeamovie.source.local.database.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query ("SELECT * FROM questions")
    fun getQuestions() : Flow<List<QuestionEntity>>

}