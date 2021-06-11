package com.example.recommendmeamovie.ui.search

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.SearchRepository
import com.example.recommendmeamovie.util.Event
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentQuery = MutableLiveData<String>()

    val results = currentQuery.switchMap { query ->
        repository.getSearchResults(query).asLiveData(Dispatchers.IO)
    }.cachedIn(viewModelScope)

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
        currentQuery.value = query
    }

}