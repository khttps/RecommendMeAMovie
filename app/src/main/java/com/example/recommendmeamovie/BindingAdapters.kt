package com.example.recommendmeamovie

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.network.Movie
import com.example.recommendmeamovie.ui.main.MovieAdapter
import com.squareup.picasso.Picasso

const val IMAGE_URL = "https://image.tmdb.org/t/p/w185"

@BindingAdapter("listData")
fun setListData(recyclerView: RecyclerView, movies: List<Movie>?) {

    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(movies)
}

@BindingAdapter("poster")
fun setPoster(imageView : ImageView, path : String?) {
    Picasso
        .get()
        .load(IMAGE_URL + path)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
        .into(imageView)
}
