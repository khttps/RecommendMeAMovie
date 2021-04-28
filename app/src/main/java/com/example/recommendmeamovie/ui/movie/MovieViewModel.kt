package com.example.recommendmeamovie.ui.movie

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.*
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.repository.MovieDetailsRepository
import com.example.recommendmeamovie.util.Utils
import com.example.recommendmeamovie.work.NotificationWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val notificationManager: NotificationManager,
    @ApplicationContext private val applicationContext: Context,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {


    private val movieId
        get() = savedStateHandle
            .get<Bundle>("args")
            ?.getLong("movieId")

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails>
        get() = _movieDetails

    private val _empty = MutableLiveData<Boolean>()
    val empty : LiveData<Boolean>
        get() = _empty

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

        if (Utils.isConnected(applicationContext)) {
            viewModelScope.launch {
                getMovieDetails()
            }
        } else {
            _empty.value = true
        }

    }

    private suspend fun getMovieDetails() {
        withContext(Dispatchers.IO) {
            _movieDetails.postValue(
                movieId?.let { movieDetailsRepository.getMovieDetails(it) }
            )
        }
    }

    @SuppressLint("RestrictedApi")
    fun scheduleNotification() {

        val data = Data.Builder().apply {
            put("movieId", movieId)
            put("movieName", _movieDetails.value?.title)
            put("moviePoster", _movieDetails.value?.poster)
        }.build()

        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(data)
            .setInitialDelay(1L, TimeUnit.DAYS)
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            WorkManager.getInstance(applicationContext).enqueue(notificationWorkRequest)
        }
    }
}