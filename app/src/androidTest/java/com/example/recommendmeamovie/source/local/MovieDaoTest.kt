package com.example.recommendmeamovie.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MovieDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var movieDatabase : MovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun startup() {
        hiltRule.inject()
        movieDao = movieDatabase.movieDao

    }

    @InternalCoroutinesApi
    @Test
    fun insertAndRead() = runBlockingTest{
        val movieEntity = MovieEntity(0, "Chungking Express","", "1994-9-26", "top_rated")
        movieDao.addMovie(movieEntity)

        val movies = movieDao.getMovies("top_rated").first()
        assertThat(movies).contains(movieEntity)

    }


    @After
    fun teardown() {
        movieDatabase.clearAllTables()
        movieDatabase.close()
    }

}