package com.example.recommendmeamovie.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
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

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MovieDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test-db")
    lateinit var movieDatabase: MovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun startup() {
        hiltRule.inject()
        movieDao = movieDatabase.movieDao

    }

    @Test
    fun insertAndRead() = runBlockingTest {
        val movieEntity = MovieEntity(0, "Chungking Express", "", "1994-9-26", "top_rated")
        movieDao.addMovie(movieEntity)

        val movies = movieDao.getMovies("top_rated").first()
        assertThat(movies).contains(movieEntity)

    }

    @Test
    fun insertListAndRead() = runBlockingTest {
        val movieList = listOf(
            MovieEntity(0, "Zack Snyder's Justice League", "", "2021-3-18", "popular"),
            MovieEntity(1, "Parasite", "", "2019-5-30", "popular"),
            MovieEntity(2, "Evangelion 3.0 + 1.0 Thrice Upon A Time", "", "2021-3-8", "popular")
        )
        movieDao.addMovieList(movieList)

        val movies = movieDao.getMovies("popular").first()
        assertThat(movies).containsAtLeastElementsIn(movieList)
    }


    @Test
    fun deleteAndRead() = runBlockingTest {
        val movieEntity = MovieEntity(0, "Chungking Express", "", "1994-9-26", "top_rated")
        movieDao.addMovie(movieEntity)

        movieDao.deleteMovies("top_rated")

        val movies = movieDao.getMovies("top_rated").first()
        assertThat(movies).doesNotContain(movieEntity)

    }


    @After
    fun teardown() {
        movieDatabase.close()
    }

}
