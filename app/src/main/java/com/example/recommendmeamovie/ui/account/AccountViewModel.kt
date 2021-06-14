package com.example.recommendmeamovie.ui.account

import androidx.lifecycle.*
import com.example.recommendmeamovie.repository.interfaces.AccountRepository
import com.example.recommendmeamovie.repository.interfaces.MovieRepository
import com.example.recommendmeamovie.repository.interfaces.SessionRepository
import com.example.recommendmeamovie.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val sessionRepository: SessionRepository,
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val sessionId = sessionRepository.getSessionId().asLiveData()

    val account = accountRepository.getAccount().asLiveData()



    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.clearAccount()
            _eventNavigateUp.postValue(Event(Unit))
        }
    }

    private val _eventNavigateUp = MutableLiveData<Event<Unit>>()
    val eventNavigateUp: LiveData<Event<Unit>>
        get() = _eventNavigateUp

}