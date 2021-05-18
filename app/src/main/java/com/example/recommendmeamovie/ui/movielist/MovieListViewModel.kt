package com.example.recommendmeamovie.ui.movielist

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.SearchResultsRepository
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
@Inject constructor(private val searchResultsRepository: SearchResultsRepository,
                    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _listResource = MutableLiveData<Resource<List<Movie>>>()
    val listResource : LiveData<Resource<List<Movie>>>
        get() = _listResource

    val list = Transformations.map(_listResource) {
        it.data
    }

    private val _eventNavigateToMovie = MutableLiveData<Movie?>()
    val eventNavigateToMovie : LiveData<Movie?>
        get() = _eventNavigateToMovie


    fun navigateToMovie(movie : Movie) {
        _eventNavigateToMovie.value = movie
    }

    fun navigateToMovieCompleted() {
        _eventNavigateToMovie.value = null
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchResultsRepository.getSearchResults(query).collect {
                _listResource.postValue(it)
            }
        }
    }


}