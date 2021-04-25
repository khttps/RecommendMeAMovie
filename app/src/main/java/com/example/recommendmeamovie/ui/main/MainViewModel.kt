package com.example.recommendmeamovie.ui.main

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies : LiveData<List<Movie>>
        get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies : LiveData<List<Movie>>
        get() = _topRatedMovies

    private val _popularLoaded = MutableLiveData<Boolean>()
    val popularLoaded : LiveData<Boolean>
        get() = _popularLoaded

    private val _topRatedLoaded = MutableLiveData<Boolean>()
    val topRatedLoaded : LiveData<Boolean>
        get() = _topRatedLoaded

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

            _popularLoaded.postValue( true)

            _topRatedMovies.postValue(
                movieRepository.getTopRatedMovies()
            )

            _topRatedLoaded.postValue(true)
        }
    }


}