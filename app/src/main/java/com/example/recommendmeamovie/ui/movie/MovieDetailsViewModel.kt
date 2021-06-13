package com.example.recommendmeamovie.ui.movie

import android.content.Context
import androidx.lifecycle.*
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.repository.interfaces.MovieDetailsRepository
import com.example.recommendmeamovie.util.Resource
import com.example.recommendmeamovie.util.scheduleNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieDetailsRepository,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val movieId = savedStateHandle.get<Long>("movieId")

    private val _movieDetails = MutableLiveData<Resource<MovieDetails>>()
    val movieDetails: LiveData<Resource<MovieDetails>>
        get() = _movieDetails

    val year = Transformations.map(movieDetails) {
        it?.data?.releaseDate?.substringBefore("-")
    }

    val director = Transformations.map(movieDetails) { details ->
        details?.data?.crew?.first {
            it.role.equals("Director", true)
        }
    }

    val isLoading: LiveData<Boolean> = Transformations.map(movieDetails) {
        it is Resource.Loading
    }

    val movieLoaded: LiveData<Boolean> = Transformations.map(movieDetails) {
        it is Resource.Success
    }

    val loadingFailed: LiveData<Boolean> = Transformations.map(movieDetails) {
        it is Resource.Error
    }

    init {
        if (movieId == null)
            _movieDetails.value = Resource.Error(Throwable("Failed to load movie info."))
        else {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getMovieDetails(movieId).collect {
                    _movieDetails.postValue(it)
                }
            }
        }
    }

    fun scheduleMovieNotification() {
        val data = workDataOf(
            "movieId" to movieDetails.value?.data?.id,
            "movieName" to movieDetails.value?.data?.title,
            "moviePoster" to movieDetails.value?.data?.poster
        )

        WorkManager.getInstance(context).scheduleNotification(data)
    }
}