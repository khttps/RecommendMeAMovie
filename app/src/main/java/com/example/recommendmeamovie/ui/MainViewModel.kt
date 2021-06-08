package com.example.recommendmeamovie.ui

import androidx.lifecycle.*
import com.example.recommendmeamovie.repository.SessionRepository
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SessionRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val loggedIn = repository.loginStatus.asLiveData(Dispatchers.IO)

    private val _session = MutableLiveData<Resource<String>>()
    val session: LiveData<Resource<String>>
        get() = _session

    fun startSession() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSessionId().collect {
                _session.postValue(it)
            }
        }
    }

    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearSession()
        }
    }




}