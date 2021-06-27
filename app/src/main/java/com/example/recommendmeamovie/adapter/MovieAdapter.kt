package com.example.recommendmeamovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.ListItemPosterBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Constants

class MovieAdapter(
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(ListItemCallbacks) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            ListItemPosterBinding.inflate(inflater)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: ListItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(Constants.IMAGE_URL + movie.poster)
                .placeholder(R.drawable.loading_animation)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fallback(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .centerCrop()
                .into(binding.poster)
        }
    }
}