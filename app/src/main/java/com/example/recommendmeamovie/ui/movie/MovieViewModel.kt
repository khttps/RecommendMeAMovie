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
import com.example.recommendmeamovie.work.NotificationWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
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
            ?.getLong("movieId") ?: 0

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails>
        get() = _movieDetails

    val empty = Transformations.map(_movieDetails) {
        it == null
    }

    val year = Transformations.map(_movieDetails) {
        it.releaseDate.substringBefore("-")
    }

    val director = Transformations.map(_movieDetails) { details ->
        details.crew?.first {
            it.role.equals("Director", true)
        }
    }

    init {
        viewModelScope.launch {
            loadMovieDetails()
        }
    }

    @SuppressLint("RestrictedApi")
    fun scheduleNotification() {
        val data = Data.Builder().apply {
            put("movieId", movieId)
            put("movieName", movieDetails.value?.title)
            put("moviePoster", movieDetails.value?.poster)
        }.build()

        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(data)
            .setInitialDelay(3L, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(notificationWorkRequest)
    }

    private suspend fun loadMovieDetails() {
        withContext(Dispatchers.IO) {
            movieDetailsRepository.getMovieDetails(movieId).collect {
                _movieDetails.postValue(it)
            }
        }
    }
}