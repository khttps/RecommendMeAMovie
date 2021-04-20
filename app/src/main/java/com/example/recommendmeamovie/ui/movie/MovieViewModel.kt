package com.example.recommendmeamovie.ui.movie

import android.os.Bundle
import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.repositories.MovieDetailsRepository
import com.example.recommendmeamovie.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor(private val movieDetailsRepository: MovieDetailsRepository,
                    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val movieId
        get() = savedStateHandle
            .get<Bundle>("args")
            ?.getLong("movieId")

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails>
        get() = _movieDetails

    private val _year = Transformations.map(_movieDetails) {
        Utils.getReleaseYear(it.releaseDate)
    }
    val year : LiveData<String>
        get() = _year

    private val _genres = Transformations.map(_movieDetails) { details ->
        details.genres
    }
    val genres : LiveData<String?>
        get() = _genres

    private val _director = Transformations.map(_movieDetails) { details ->
        details.crew?.first {
            it.role.equals("Director", true)
        }
    }
    val director : LiveData<Credit?>
        get() = _director

    init {
        viewModelScope.launch {
            getMovieDetails()
        }
    }

    private suspend fun getMovieDetails() {
        withContext(Dispatchers.IO) {
            _movieDetails.postValue(
                movieId?.let { movieDetailsRepository.getMovieDetails(it) }
            )
        }
    }

}