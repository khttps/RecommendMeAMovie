package com.example.recommendmeamovie.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.source.remote.Genre
import com.example.recommendmeamovie.util.Constants.IMAGE_URL
import com.squareup.picasso.Picasso

object Utils {

    fun getGenreString(genres : List<Genre>?) : String {
        if (genres != null) {
            return genres.take(2).joinToString(separator = ", ", transform = {
                it.name
            })
        }
        return ""
    }

    fun bindImage(imagePath : String?, imageView : ImageView, errorResId : Int) {
        Picasso
            .get()
            .load(IMAGE_URL + imagePath)
            .placeholder(R.drawable.loading_animation)
            .apply {
                if (errorResId != 0)
                    error(errorResId)
            }.into(imageView)
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

    fun isConnected(context : Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        return activeNetwork != null && isConnected
    }


}