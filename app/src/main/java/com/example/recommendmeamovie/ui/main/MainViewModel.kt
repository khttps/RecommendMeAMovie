package com.example.recommendmeamovie.ui.main

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    val popularMovies = movieRepository.popularMovies.asLiveData(Dispatchers.IO)
    val topRatedMovies = movieRepository.topRatedMovies.asLiveData(Dispatchers.IO)

    val popularLoaded = Transformations.map(popularMovies) {
        it.isNotEmpty()
    }

    val topRatedLoaded = Transformations.map(topRatedMovies) {
        it.isNotEmpty()
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