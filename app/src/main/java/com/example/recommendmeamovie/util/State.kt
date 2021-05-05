package com.example.recommendmeamovie.util

import java.lang.Exception

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<out R>(val data : R) : State<R>()
    data class Error(val exception: Exception) : State<Nothing>()
}
