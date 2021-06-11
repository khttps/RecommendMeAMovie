package com.example.recommendmeamovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Constants.IMAGE_URL

class MoviePagingAdapter(
    private val listener: OnMovieClickListener,
    private val resId: Int
) : PagingDataAdapter<Movie, MoviePagingAdapter.MovieViewHolder>(ListItemCallbacks) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            resId, parent, false
        )

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var movie: Movie

        fun bind(movie: Movie) {
            this.movie = movie

            val poster = itemView.findViewById<ImageView>(R.id.poster)
            val title = itemView.findViewById<TextView>(R.id.title)
            val year = itemView.findViewById<TextView>(R.id.year)

            Glide.with(itemView)
                .load(IMAGE_URL + movie.poster)
                .placeholder(R.drawable.loading_animation)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fallback(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .centerCrop()
                .into(poster)

            title.text = movie.title
            year.text = movie.releaseDate?.substringBefore("-")
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (absoluteAdapterPosition != RecyclerView.NO_POSITION)
                listener.onMovieClick(movie)
        }
    }

    object ListItemCallbacks : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}