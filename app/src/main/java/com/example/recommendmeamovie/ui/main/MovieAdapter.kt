package com.example.recommendmeamovie.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.databinding.MainListItemBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieDTO


class MovieAdapter(private val listener : OnMovieClickListener) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(ListItemCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(MainListItemBinding.inflate(inflater))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(val binding: MainListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(movie : Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (absoluteAdapterPosition != RecyclerView.NO_POSITION)
                listener.onMovieClick(binding.movie!!)
        }

    }

    class ListItemCallbacks : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    interface OnMovieClickListener {
        fun onMovieClick(movie : Movie)
    }

}