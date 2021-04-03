package com.example.recommendmeamovie.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recommendmeamovie.network.Movie

class MovieListViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Movie>>()
    val list : LiveData<List<Movie>>
        get() = _list

}