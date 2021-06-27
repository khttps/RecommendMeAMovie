package com.example.recommendmeamovie.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recommendmeamovie.domain.Movie

object ListItemCallbacks : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}



