package com.example.recommendmeamovie.ui

import androidx.lifecycle.*
import com.example.recommendmeamovie.repository.interfaces.AccountRepository
import com.example.recommendmeamovie.repository.interfaces.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SessionRepository,
    private val accountRepository: AccountRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val session = repository.getSessionId().asLiveData(Dispatchers.IO)

    val account = session.switchMap {
        accountRepository.getAccount().asLiveData()
    }

}