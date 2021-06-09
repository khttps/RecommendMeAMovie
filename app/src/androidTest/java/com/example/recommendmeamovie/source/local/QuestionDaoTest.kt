package com.example.recommendmeamovie.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.recommendmeamovie.source.local.dao.QuestionDao

import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
@ExperimentalCoroutinesApi
class QuestionDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test-db")
    lateinit var database: MovieDatabase
    private lateinit var questionDao: QuestionDao

    @Before
    fun setup() {
        hiltRule.inject()
        questionDao = database.questionDao
    }

    @Test
    fun readPrePopulatedList_returnsNonEmptyList() = runBlockingTest {
        val questions = questionDao.getQuestions().first()
        assertThat(questions).isNotEmpty()
    }

    @After
    fun tearDown() {
        database.close()
    }



}