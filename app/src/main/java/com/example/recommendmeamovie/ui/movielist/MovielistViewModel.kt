package com.example.recommendmeamovie.ui.movielist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.recommendmeamovie.repository.interfaces.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovielistViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    // TODO: Implement the ViewModel
}