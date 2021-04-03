package com.example.recommendmeamovie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.network.Movie
import com.example.recommendmeamovie.network.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

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
            loadPreviewMovies()
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

    private suspend fun loadPreviewMovies() {
        withContext(Dispatchers.IO) {
            _popularMovies.postValue(
                MovieService
                    .retrofitService
                    .getMovies("popular", BuildConfig.API_KEY)
                    .results
            )

            _topRatedMovies.postValue(
                MovieService
                    .retrofitService
                    .getMovies("top_rated", BuildConfig.API_KEY)
                    .results
            )
        }
    }


}