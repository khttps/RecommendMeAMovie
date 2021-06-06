package com.example.recommendmeamovie.ui

import android.net.Uri
import androidx.lifecycle.*
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.NetworkToken
import com.example.recommendmeamovie.util.Constants
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: MovieApiService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val token = flow<Resource<NetworkToken>> {
        emit(Resource.Loading())
        try {
           emit(Resource.Success(service.getToken()))

        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
        }
    }.asLiveData(Dispatchers.IO)

    val url = Transformations.map(token) {
        it.data?.let { token ->
            if (token.success) {
                getAuthUrl(token.requestToken)
            } else
                null
        }
    }

    private fun getAuthUrl(token: String) = Uri.Builder()
        .scheme("https")
        .authority(Constants.AUTH_URL)
        .appendPath("authenticate")
        .appendPath(token)
        .appendQueryParameter(
            "redirect_to",
            Constants.REDIRECT_URI
        ).build()
}