package com.example.recommendmeamovie.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.NetworkToken
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val service: MovieApiService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val token = flow<Resource<NetworkToken>> {
        emit(Resource.Loading())
        print("XXXLoading")

        try {
           emit(Resource.Success(service.getToken()))
            print("XXXSuccess")

        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
            print("XXXFailed")
        }
    }.asLiveData(Dispatchers.IO)


}