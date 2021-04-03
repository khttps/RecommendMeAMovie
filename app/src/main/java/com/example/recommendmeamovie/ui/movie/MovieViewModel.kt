package com.example.recommendmeamovie.ui.movie

import androidx.lifecycle.*
import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.network.Credits
import com.example.recommendmeamovie.network.Crew
import com.example.recommendmeamovie.network.MovieDetails
import com.example.recommendmeamovie.network.MovieService
import com.example.recommendmeamovie.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MovieViewModel(private val movieId : Long) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails>
        get() = _movieDetails

    private val _credits = MutableLiveData<Credits>()
    val credits : LiveData<Credits>
        get() = _credits

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


    private val _director = Transformations.map(_credits) { credits ->
        credits.crew?.first {
            it.job.equals("Director", true)
        }
    }
    val director : LiveData<Crew?>
        get() = _director

    init {
        viewModelScope.launch {
            getMovieDetails()
        }
    }


    private suspend fun getMovieDetails() {
        withContext(Dispatchers.IO) {
            _movieDetails.postValue(
                MovieService
                    .retrofitService
                    .getMovieDetails(movieId, BuildConfig.API_KEY)
            )

            _credits.postValue(
                MovieService
                    .retrofitService
                    .getMovieCredits(movieId, BuildConfig.API_KEY)
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