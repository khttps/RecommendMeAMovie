package com.example.recommendmeamovie.utils

import com.example.recommendmeamovie.network.Genre

object Utils {

    fun getGenreString(genres : List<Genre>) : String {
        return genres.joinToString(separator = ", ", transform = {
                it.name
            })
    }
}