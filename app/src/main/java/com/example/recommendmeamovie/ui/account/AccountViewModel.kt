package com.example.recommendmeamovie.ui.account

import androidx.lifecycle.*
import com.example.recommendmeamovie.repository.interfaces.AccountRepository
import com.example.recommendmeamovie.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val account = repository.getAccount().asLiveData()

    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAccount()
            _eventNavigateUp.postValue(Event(Unit))
        }
    }

    private val _eventNavigateUp = MutableLiveData<Event<Unit>>()
    val eventNavigateUp: LiveData<Event<Unit>>
        get() = _eventNavigateUp

}