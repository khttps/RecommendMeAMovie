package com.example.recommendmeamovie.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recommendmeamovie.source.remote.MovieDTO

class MovieListViewModel : ViewModel() {

    private val _list = MutableLiveData<List<MovieDTO>>()
    val list : LiveData<List<MovieDTO>>
        get() = _list

}