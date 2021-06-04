package com.example.recommendmeamovie.ui.search

import androidx.lifecycle.*
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

    private val _searchResults = MutableLiveData<Resource<List<Movie>>>()
    val searchResults: LiveData<Resource<List<Movie>>>
        get() = _searchResults


    private val _eventNavigateToMovie = MutableLiveData<Event<Movie>>()
    val eventNavigateToMovie: LiveData<Event<Movie>>
        get() = _eventNavigateToMovie


    fun navigateToMovie(movie: Movie) {
        _eventNavigateToMovie.value = Event(movie)
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSearchResults(query).collect {
                _searchResults.postValue(it)
            }
        }
    }

}