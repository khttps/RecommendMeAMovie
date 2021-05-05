package com.example.recommendmeamovie.ui.recommend

import androidx.lifecycle.*
import com.example.recommendmeamovie.repository.QuestionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    questionRepository: QuestionRepositoryImpl
    ) : ViewModel() {



}