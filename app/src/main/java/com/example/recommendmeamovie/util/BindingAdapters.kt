package com.example.recommendmeamovie.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.adapter.CreditsAdapter
import com.example.recommendmeamovie.adapter.MoviePagingAdapter

@BindingAdapter("creditsList")
fun setCreditsList(recyclerView: RecyclerView, credits: List<Credit>?) {
    val adapter = recyclerView.adapter as CreditsAdapter
    adapter.submitList(credits)
}

@BindingAdapter("poster")
fun setPoster(imageView : ImageView, path : String?) {
    Glide.with(imageView.context)
        .load(Constants.IMAGE_URL + path)
        .placeholder(R.drawable.loading_animation)
        .into(imageView)
}

@BindingAdapter("profile")
fun setProfile(imageView : ImageView, path : String?) {
    Glide.with(imageView.context)
        .load(Constants.IMAGE_URL + path)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_placeholder)
        .into(imageView)
}
