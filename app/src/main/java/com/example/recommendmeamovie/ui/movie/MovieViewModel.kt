package com.example.recommendmeamovie.ui.movie

import android.content.Context
import androidx.lifecycle.*
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.recommendmeamovie.repository.MovieDetailsRepository
import com.example.recommendmeamovie.util.Resource
import com.example.recommendmeamovie.util.scheduleNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val movieId = savedStateHandle.get<Long>("movieId")!!

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

    val isLoading: LiveData<Boolean> = Transformations.map(movieDetailsResource) {
        it is Resource.Loading
    }

    val movieLoaded: LiveData<Boolean> = Transformations.map(movieDetailsResource) {
        it is Resource.Success
    }

    val loadingFailed: LiveData<Boolean> = Transformations.map(movieDetailsResource) {
        it is Resource.Error
    }

    fun scheduleMovieNotification() {
        val data = workDataOf(
            "movieId" to movieId,
            "movieName" to movieDetails.value?.title,
            "moviePoster" to movieDetails.value?.poster
        )

        WorkManager.getInstance(context).scheduleNotification(data)
    }
}