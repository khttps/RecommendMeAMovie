package com.example.recommendmeamovie.ui.account

import androidx.lifecycle.*
import com.example.recommendmeamovie.source.datastore.SessionDataManager
import com.example.recommendmeamovie.source.remote.dto.NetworkAccount
import com.example.recommendmeamovie.source.remote.service.AccountApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountApiService: AccountApiService,
    private val sessionDataManager: SessionDataManager,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val sessionId = sessionDataManager.getSessionId().asLiveData(Dispatchers.IO)

    val account = MutableLiveData<NetworkAccount>()

    fun getAccount(sessionId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountApiService.getAccountDetails(sessionId = sessionId).let {
                account.postValue(it)
            }
        }
    }

    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            sessionDataManager.clearSession()

        }
    }

}