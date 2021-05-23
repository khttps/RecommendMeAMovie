package com.example.recommendmeamovie.ui.search

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.SearchResultsRepository
import com.example.recommendmeamovie.util.Event
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchResultsRepository: SearchResultsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _listResource = MutableLiveData<Resource<List<Movie>>>()
    val listResource: LiveData<Resource<List<Movie>>>
        get() = _listResource

    val list = Transformations.map(_listResource) {
        it.data
    }

    private val _eventNavigateToMovie = MutableLiveData<Event<Movie>>()
    val eventNavigateToMovie: LiveData<Event<Movie>>
        get() = _eventNavigateToMovie


    fun navigateToMovie(movie: Movie) {
        _eventNavigateToMovie.value = Event(movie)
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchResultsRepository.getSearchResults(query).collect {
                _listResource.postValue(it)
            }
        }
    }

}