package com.example.recommendmeamovie.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.recommendmeamovie.MainCoroutineRule

import com.example.recommendmeamovie.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
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
    @Named("test_db")
    lateinit var database: MovieDatabase

    private lateinit var questionDao: QuestionDao
    private lateinit var movieDao: MovieDao

    @Before
    fun setup() {
        hiltRule.inject()
        questionDao = database.questionDao
    }

    @After
    fun tearDown() {
        database.clearAllTables()
    }

//    @Test
//    fun checkInsertWithMovieDao() = runBlockingTest{
//        val movie = MovieEntity(1, "", "", "", "popular")
//        movieDao.addMovie(movie)
//
//        lateinit var movies : List<MovieEntity>
//
//        movieDao.getMovies("popular").collect {
//            movies = it
//
//        }
//        assertThat(movies).contains(movie)
//    }

    @Test
    fun queryQuestionDao_returnsANonEmptyList() = runBlockingTest {
        val questions = questionDao.observeAllQuestions().getOrAwaitValue()
        assertThat(questions).isNotEmpty()
    }

}