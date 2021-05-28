package com.example.recommendmeamovie.ui.movie

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ApplicationProvider
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.getOrAwaitValue
import com.example.recommendmeamovie.repository.FakeMovieDetailsRepository
import com.example.recommendmeamovie.takeUntil
import com.example.recommendmeamovie.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FakeMovieDetailsRepository
    private lateinit var context: Context
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        repository = FakeMovieDetailsRepository()
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun getMovieDetails_movieNotFound_returnsResourceError() {
        savedStateHandle = SavedStateHandle().apply {
            set("movieId", 52L)
        }

        viewModel = MovieDetailsViewModel(
            repository,
            context,
            savedStateHandle
        )

        val loading = viewModel.movieDetails.getOrAwaitValue()
        assertThat(loading).isInstanceOf(Resource.Loading::class.java)

        val error = viewModel.movieDetails.takeUntil {
            it !is Resource.Loading
        }
        assertThat(error).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun getMovieDetails_movieFound_returnsResourceSuccess() {
        savedStateHandle = SavedStateHandle().apply {
            set("movieId", 1L)
        }

        viewModel = MovieDetailsViewModel(
            repository,
            context,
            savedStateHandle
        )

        val loading = viewModel.movieDetails.getOrAwaitValue()
        assertThat(loading).isInstanceOf(Resource.Loading::class.java)

        val success = viewModel.movieDetails.takeUntil {
            it !is Resource.Loading
        }
        assertThat(success).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun getMovieId_movieIdIsNull_loadingFails() {
        savedStateHandle = SavedStateHandle()

        viewModel = MovieDetailsViewModel(
            repository,
            context,
            savedStateHandle
        )

        val loadingFailed = viewModel.loadingFailed.getOrAwaitValue()
        assertThat(loadingFailed).isEqualTo(true)
    }


}