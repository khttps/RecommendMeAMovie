package com.example.recommendmeamovie.ui.recommend

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.recommendmeamovie.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val questionRepository: QuestionRepository
) : ViewModel() {


}