package com.example.recommendmeamovie.ui.movie

import androidx.lifecycle.*
import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.repositories.MovieDetailsRepository
import com.example.recommendmeamovie.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MovieViewModel(private val movieId : Long) : ViewModel() {

    private val movieDetailsRepository = MovieDetailsRepository()

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
                movieDetailsRepository.getMovieDetails(movieId)
            )
        }
    }

}

class MovieViewModelFactory(private val movieId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java))
            return MovieViewModel(movieId) as T

        throw IllegalArgumentException("Unrecognized ViewModel")
    }

}