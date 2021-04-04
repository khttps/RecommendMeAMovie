package com.example.recommendmeamovie.utils

import com.example.recommendmeamovie.source.remote.Genre

object Utils {

    fun getGenreString(genres : List<Genre>) : String {
        return genres.joinToString(separator = ", ", transform = {
                it.name
            })
    }

    fun getReleaseYear(releaseDate : String) : String {
        return releaseDate.substringBefore("-")
    }
}