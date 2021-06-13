package com.example.recommendmeamovie.ui.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.interfaces.MovieRepository
import com.example.recommendmeamovie.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _results = MutableLiveData<PagingData<Movie>>()
    val results: LiveData<PagingData<Movie>>
        get() = _results

    private var searchJob: Job? = null

    private val _eventNavigateToMovie = MutableLiveData<Event<Movie>>()
    val eventNavigateToMovie: LiveData<Event<Movie>>
        get() = _eventNavigateToMovie

    private val _eventNavigateUp = MutableLiveData<Event<Unit>>()
    val eventNavigateUp: LiveData<Event<Unit>>
        get() = _eventNavigateUp

    fun navigateToMovie(movie: Movie) {
        _eventNavigateToMovie.value = Event(movie)
    }

    fun navigateUp() {
        _eventNavigateUp.value = Event(Unit)
    }

    fun getSearchResults(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            repository.getPagedSearchResults(query).cachedIn(viewModelScope).collect {
                _results.postValue(it)
            }
        }
    }

}