package com.example.recommendmeamovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.recommendmeamovie.domain.Movie

class MovieAdapter(
    private val listener: OnMovieClickListener?,
    private val resId: Int
) : PagingDataAdapter<Movie, MovieViewHolder>(ListItemCallbacks) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            resId, parent, false
        )

        return MovieViewHolder(listener, itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }
}