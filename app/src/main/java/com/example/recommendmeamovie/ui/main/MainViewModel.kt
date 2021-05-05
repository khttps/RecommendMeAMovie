package com.example.recommendmeamovie.ui.main

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies : LiveData<List<Movie>>
        get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies : LiveData<List<Movie>>
        get() = _topRatedMovies

    val popularLoaded = Transformations.map(_popularMovies) {
        it.isNotEmpty()
    }

    val topRatedLoaded = Transformations.map(_topRatedMovies) {
        it.isNotEmpty()
    }

    init {
        viewModelScope.launch {
            movieRepository.apply {
                getPopularMovies().collect { _popularMovies.postValue(it) }
                getTopRatedMovies().collect { _topRatedMovies.postValue(it) }
            }
        }
    }

    private val _eventNavigateToRecommend = MutableLiveData<Boolean>()
    val eventNavigateToRecommend : LiveData<Boolean>
        get() = _eventNavigateToRecommend

    private val _eventNavigateToMovie = MutableLiveData<Movie?>()
    val eventNavigateToMovie : LiveData<Movie?>
        get() = _eventNavigateToMovie


    fun navigateToRecommend() {
        _eventNavigateToRecommend.value = true
    }

    fun navigateToRecommendCompleted() {
        _eventNavigateToRecommend.value = false
    }

    fun navigateToMovie(movie : Movie) {
        _eventNavigateToMovie.value = movie
    }

    fun navigateToMovieCompleted() {
        _eventNavigateToMovie.value = null
    }

}