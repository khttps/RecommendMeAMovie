package com.example.recommendmeamovie.ui.movielist

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.SearchResultsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
@Inject constructor(private val searchResultsRepository: SearchResultsRepository,
                    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _list = MutableLiveData<List<Movie>>()
    val list : LiveData<List<Movie>>
        get() = _list

    private val _empty = Transformations.map(_list) {
        it.isNullOrEmpty()
    }
    val empty : LiveData<Boolean>
        get() = _empty

    private val _eventNavigateToMovie = MutableLiveData<Movie?>()
    val eventNavigateToMovie : LiveData<Movie?>
        get() = _eventNavigateToMovie


    fun navigateToMovie(movie : Movie) {
        _eventNavigateToMovie.value = movie
    }

    fun navigateToMovieCompleted() {
        _eventNavigateToMovie.value = null
    }

    private suspend fun getSearchResults(query: String) {
        withContext(Dispatchers.IO) {
            _list.postValue(
                searchResultsRepository.getSearchResults(query)
            )
        }
    }

    fun executeQuery(query: String) {
        viewModelScope.launch {
            getSearchResults(query)
        }
    }


}