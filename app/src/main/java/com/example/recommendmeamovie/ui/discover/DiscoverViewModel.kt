package com.example.recommendmeamovie.ui.discover

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.MovieRepository
import com.example.recommendmeamovie.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val popularMovies = movieRepository.getPopularMovies().asLiveData()
    val topRatedMovies = movieRepository.getTopRatedMovies().asLiveData()

    private val _eventNavigateToRecommend = MutableLiveData<Event<Unit>>()
    val eventNavigateToRecommend: LiveData<Event<Unit>>
        get() = _eventNavigateToRecommend

    private val _eventNavigateToMovie = MutableLiveData<Event<Movie>>()
    val eventNavigateToMovie: LiveData<Event<Movie>>
        get() = _eventNavigateToMovie


    fun navigateToRecommend() {
        _eventNavigateToRecommend.value = Event(Unit)
    }

    fun navigateToMovie(movie: Movie) {
        _eventNavigateToMovie.value = Event(movie)
    }

}