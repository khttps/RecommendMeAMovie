package com.example.recommendmeamovie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies : LiveData<List<Movie>>
        get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies : LiveData<List<Movie>>
        get() = _topRatedMovies


    private val _eventNavigateToRecommend = MutableLiveData<Boolean>()
    val eventNavigateToRecommend : LiveData<Boolean>
        get() = _eventNavigateToRecommend


    private val _eventNavigateToMovie = MutableLiveData<Movie?>()
    val eventNavigateToMovie : LiveData<Movie?>
        get() = _eventNavigateToMovie


    init {
        viewModelScope.launch {
            getMoviePreviews()
        }
    }

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

    private suspend fun getMoviePreviews() {
        withContext(Dispatchers.IO) {
            _popularMovies.postValue(
                movieRepository.getPopularMovies()
            )

            _topRatedMovies.postValue(
                movieRepository.getTopRatedMovies()
            )
        }
    }


}