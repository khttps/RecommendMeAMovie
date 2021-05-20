package com.example.recommendmeamovie.ui.movie

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.work.workDataOf
import com.example.recommendmeamovie.repository.MovieDetailsRepository
import com.example.recommendmeamovie.util.scheduleNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    @ApplicationContext private val applicationContext: Context,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val movieId = savedStateHandle.get<Long>("movieId") ?: 0

    val movieDetailsResource = movieDetailsRepository.getMovieDetails(movieId).asLiveData()
    val movieDetails = Transformations.map(movieDetailsResource) {
        it.data
    }

    val year = Transformations.map(movieDetails) {
        it?.releaseDate?.substringBefore("-")
    }

    val director = Transformations.map(movieDetails) { details ->
        details?.crew?.first {
            it.role.equals("Director", true)
        }
    }

    fun scheduleMovieNotification() {
        val data = workDataOf(
            "movieId" to movieId,
            "movieName" to movieDetails.value?.title,
            "moviePoster" to movieDetails.value?.poster
        )

        scheduleNotification(data, applicationContext)
    }
}