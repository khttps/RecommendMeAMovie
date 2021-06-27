package com.example.recommendmeamovie.ui.account

import androidx.lifecycle.*
import androidx.lifecycle.switchMap
import com.example.recommendmeamovie.domain.Account
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.repository.interfaces.AccountRepository
import com.example.recommendmeamovie.repository.interfaces.MovieRepository
import com.example.recommendmeamovie.repository.interfaces.SessionRepository
import com.example.recommendmeamovie.util.Event
import com.example.recommendmeamovie.util.Resource
import com.example.recommendmeamovie.util.combineWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val account = accountRepository.getAccount().asLiveData()

    val watchlist = movieRepository.getUserWatchlist().asLiveData()

    val favorites = movieRepository.getUserFavorites().asLiveData()

    private val _eventNavigateUp = MutableLiveData<Event<Unit>>()
    val eventNavigateUp: LiveData<Event<Unit>>
    get() = _eventNavigateUp

    private fun navigateUp() {
        _eventNavigateUp.postValue(Event(Unit))
    }

    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.clearAccount()
        }
        navigateUp()
    }



}