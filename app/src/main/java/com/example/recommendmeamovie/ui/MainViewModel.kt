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

    val session = repository.getSessionId().asLiveData(Dispatchers.IO)

    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearSession()
        }
    }

    
}