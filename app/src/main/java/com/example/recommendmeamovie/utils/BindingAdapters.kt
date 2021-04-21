package com.example.recommendmeamovie.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.adapters.MovieAdapter
import com.example.recommendmeamovie.adapters.CreditsAdapter

const val IMAGE_URL = "https://image.tmdb.org/t/p/w185"

@BindingAdapter("movieList")
fun setMovieList(recyclerView: RecyclerView, movies: List<Movie>?) {

    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(movies)
}

@BindingAdapter("creditsList")
fun setCreditsList(recyclerView: RecyclerView, credits: List<Credit>?) {
    val adapter = recyclerView.adapter as CreditsAdapter
    adapter.submitList(credits)
}


@BindingAdapter("poster")
fun setPoster(imageView : ImageView, path : String?) {
    Utils.bindImage(path, imageView, R.drawable.ic_broken_image)

}

@BindingAdapter("profile")
fun setProfile(imageView : ImageView, path : String?) {
    Utils.bindImage(path, imageView, R.drawable.ic_placeholder)
}
