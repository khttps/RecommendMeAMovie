package com.example.recommendmeamovie.ui.recommend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendmeamovie.domain.Question
import com.example.recommendmeamovie.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val questionRepository: QuestionRepository
    ) : ViewModel() {

     val liveData = questionRepository.getAllQuestions()



}