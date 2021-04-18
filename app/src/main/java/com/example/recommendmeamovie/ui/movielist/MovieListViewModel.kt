package com.example.recommendmeamovie.ui.movielist

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repositories.SearchResultsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MovieListViewModel(val query: String) : ViewModel() {

    private val searchResultsRepository = SearchResultsRepository()

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


    init {

        if (query.isNotEmpty()) {
            viewModelScope.launch {
                getSearchResults(query)
            }
        } else {
            _list.value = emptyList()
        }

    }

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


}

class MovieListViewModelFactory(private val query: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java))
            return MovieListViewModel(query) as T

        throw IllegalArgumentException("Unrecognized ViewModel")
    }

}