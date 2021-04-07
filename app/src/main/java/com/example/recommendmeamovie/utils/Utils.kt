package com.example.recommendmeamovie.utils

import android.widget.ImageView
import com.example.recommendmeamovie.IMAGE_URL
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.source.remote.Genre
import com.squareup.picasso.Picasso

object Utils {

    fun getGenreString(genres : List<Genre>?) : String {
        if (genres != null) {
            return genres.joinToString(separator = ", ", transform = {
                it.name
            })
        }
        return ""
    }

    fun getReleaseYear(releaseDate : String) : String {
        return releaseDate.substringBefore("-")
    }

    fun bindImage(imagePath : String?, imageView : ImageView, errorResId : Int) {
        Picasso
            .get()
            .load(IMAGE_URL + imagePath)
            .placeholder(R.drawable.loading_animation)
            .error(errorResId)
            .into(imageView)
    }



}