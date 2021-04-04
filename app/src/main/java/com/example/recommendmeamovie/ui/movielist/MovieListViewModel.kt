package com.example.recommendmeamovie.ui.movielist

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MovieListViewModel(val query: String) : ViewModel() {

    private val movieRepository = MovieRepository()

    private val _list = MutableLiveData<List<Movie>>()
    val list : LiveData<List<Movie>>
        get() = _list

    private val _eventNavigateToMovie = MutableLiveData<Movie?>()
    val eventNavigateToMovie : LiveData<Movie?>
        get() = _eventNavigateToMovie


    init {
        viewModelScope.launch {
            getSearchResults(query)
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
                movieRepository.getSearchResults(query)
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