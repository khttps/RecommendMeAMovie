package com.example.recommendmeamovie.ui.recommend

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
    ) : ViewModel() {
    // TODO: Implement the ViewModel
}