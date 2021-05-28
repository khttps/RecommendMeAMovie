package com.example.recommendmeamovie.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.recommendmeamovie.repository.FakeSearchRepository
import com.example.recommendmeamovie.takeUntil
import com.example.recommendmeamovie.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchViewModel

    private lateinit var repository: FakeSearchRepository

    @Before
    fun startup() {
        repository = FakeSearchRepository()
        viewModel = SearchViewModel(repository, SavedStateHandle())
    }

    @Test
    fun startSearch_queryMatchesATitle_returnsResourceSuccess() = runBlockingTest {
        viewModel.getSearchResults("Parasite")
        val success = viewModel.searchResults.takeUntil {
            it !is Resource.Loading
        }

        assertThat(success).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun startSearch_queryNotFound_returnsResourceError() = runBlockingTest {
        viewModel.getSearchResults("The Fly")
        val error = viewModel.searchResults.takeUntil {
            it !is Resource.Loading
        }

        assertThat(error).isInstanceOf(Resource.Error::class.java)
    }


}