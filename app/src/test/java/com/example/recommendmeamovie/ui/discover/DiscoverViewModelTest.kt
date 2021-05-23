package com.example.recommendmeamovie.ui.discover

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.recommendmeamovie.MainCoroutineTestRule
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.getOrAwaitValueTest
import com.example.recommendmeamovie.repository.FakeMovieRepository
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

    @get:Rule
    var mainCoroutineTestRule = MainCoroutineTestRule()

    private lateinit var viewModel: DiscoverViewModel

    private lateinit var repository: FakeMovieRepository

    private lateinit var movies: List<Movie>

    @Before
    fun setup() {
        val handle = SavedStateHandle()

        movies = listOf(
            Movie(0, "Zack Snyder's Justice League", "", "2021-3-18"),
            Movie(1, "Parasite", "", "2019-5-30"),
            Movie(2, "Evangelion 3.0 + 1.0 Thrice Upon A Time", "", "2021-3-8")
        )

        repository = FakeMovieRepository(movies)
        viewModel = DiscoverViewModel(repository, handle)
    }

    @Test
    fun getMovies_noConnection_returnsResourceError() = runBlockingTest {
        repository.setNetworkConnected(false)

        val loadingResource = viewModel.popularMovies.getOrAwaitValueTest()
        assertThat(loadingResource).isInstanceOf(Resource.Loading::class.java)

        val errorResource = viewModel.popularMovies.getOrAwaitValueTest()
        assertThat(errorResource).isInstanceOf(Resource.Error::class.java)

    }

    @Test
    fun getMovies_connected_returnsResourceSuccess() = runBlockingTest {
        repository.setNetworkConnected(true)

        val loadingResource = viewModel.popularMovies.getOrAwaitValueTest()
        assertThat(loadingResource).isInstanceOf(Resource.Loading::class.java)

        val successResource = viewModel.popularMovies.getOrAwaitValueTest()
        assertThat(successResource).isInstanceOf(Resource.Success::class.java)
        assertThat(successResource.data).containsAtLeastElementsIn(movies)

    }

}