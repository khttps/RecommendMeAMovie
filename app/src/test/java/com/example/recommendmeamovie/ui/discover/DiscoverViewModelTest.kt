package com.example.recommendmeamovie.ui.discover

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.getOrAwaitValue
import com.example.recommendmeamovie.repository.FakeMovieRepository
import com.example.recommendmeamovie.takeUntil
import com.example.recommendmeamovie.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DiscoverViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DiscoverViewModel

    private lateinit var repository: FakeMovieRepository

    @Before
    fun setup() {
        val handle = SavedStateHandle()
        repository = FakeMovieRepository()
        viewModel = DiscoverViewModel(repository, handle)
    }

    @Test
    fun getMovies_noConnection_returnsResourceError() = runBlockingTest {
        repository.setNetworkConnected(false)

        val loadingResource = viewModel.popularMovies.getOrAwaitValue()
        assertThat(loadingResource).isInstanceOf(Resource.Loading::class.java)

        val errorResource = viewModel.popularMovies.takeUntil {
            it !is Resource.Loading
        }
        assertThat(errorResource).isInstanceOf(Resource.Error::class.java)

    }

    @Test
    fun getMovies_connected_returnsResourceSuccess() = runBlockingTest {
        repository.setNetworkConnected(true)

        val loading = viewModel.popularMovies.getOrAwaitValue()
        assertThat(loading).isInstanceOf(Resource.Loading::class.java)

        val success = viewModel.popularMovies.takeUntil {
            it !is Resource.Loading
        }
        assertThat(success).isInstanceOf(Resource.Success::class.java)

    }

}