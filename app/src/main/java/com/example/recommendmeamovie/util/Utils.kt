package com.example.recommendmeamovie.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.source.remote.Genre
import com.squareup.picasso.Picasso

object Utils {

    private const val IMAGE_URL = "https://image.tmdb.org/t/p/w185"

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

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }


}