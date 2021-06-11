package com.example.recommendmeamovie.ui.account

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recommendmeamovie.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val account = accountRepository.getAccount().asLiveData(Dispatchers.IO)

    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.clearSession()
        }
    }

}