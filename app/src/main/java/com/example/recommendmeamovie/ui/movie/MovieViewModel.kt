package com.example.recommendmeamovie.ui.movie

import androidx.lifecycle.*
import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.MovieRepository
import com.example.recommendmeamovie.source.remote.Crew
import com.example.recommendmeamovie.source.remote.MovieDetailsDTO
import com.example.recommendmeamovie.source.remote.MovieService
import com.example.recommendmeamovie.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MovieViewModel(private val movieId : Long) : ViewModel() {

    private val movieRepository = MovieRepository()

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails>
        get() = _movieDetails

    private val _year = Transformations.map(_movieDetails) {
        it.releaseDate.substringBefore("-")
    }
    val year : LiveData<String>
        get() = _year

    private val _genres = Transformations.map(_movieDetails) { details ->
        details.genres?.let {  Utils.getGenreString(it) }
    }
    val genres : LiveData<String>
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
                movieRepository.getMovieDetails(movieId)
            )
        }
    }

}

