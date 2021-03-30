package com.example.recommendmeamovie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.databinding.PreviewMovieItemBinding
import com.example.recommendmeamovie.network.Movie


class MovieAdapter() : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(ListItemCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(PreviewMovieItemBinding.inflate(inflater))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class MovieViewHolder(val binding: PreviewMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie : Movie) {
            binding.movie = movie
            binding.executePendingBindings()
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

}