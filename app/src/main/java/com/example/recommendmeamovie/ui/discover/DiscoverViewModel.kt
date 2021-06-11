package com.example.recommendmeamovie.ui.discover

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.MovieRepository
import com.example.recommendmeamovie.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val popularMovies = repository.getPopularMovies().asLiveData().cachedIn(viewModelScope)
    val topRatedMovies = repository.getTopRatedMovies().asLiveData().cachedIn(viewModelScope)

    private val _eventNavigateToRecommend = MutableLiveData<Event<Unit>>()
    val eventNavigateToRecommend: LiveData<Event<Unit>>
        get() = _eventNavigateToRecommend

    private val _eventNavigateToMovie = MutableLiveData<Event<Movie>>()
    val eventNavigateToMovie: LiveData<Event<Movie>>
        get() = _eventNavigateToMovie

    private val _eventNavigateToSearch = MutableLiveData<Event<Unit>>()
    val eventNavigateToSearch: LiveData<Event<Unit>>
        get() = _eventNavigateToSearch


    fun navigateToRecommend() {
        _eventNavigateToRecommend.value = Event(Unit)
    }

    fun navigateToSearch() {
        _eventNavigateToSearch.value = Event(Unit)
    }

    fun navigateToMovie(movie: Movie) {
        _eventNavigateToMovie.value = Event(movie)
    }

}