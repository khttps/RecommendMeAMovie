package com.example.recommendmeamovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Utils


class MovieAdapter(private val listener : OnMovieClickListener, private val tag : String) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
    ListItemCallbacks()
) {

    companion object {
        const val MAIN_LIST = "main"
        const val MOVIE_LIST = "movie"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       val inflater = LayoutInflater.from(parent.context)

        return MovieViewHolder(inflater.inflate(
            when (tag) {
                MAIN_LIST -> R.layout.main_list_item
                else -> R.layout.movie_list_item
            }, parent, false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var movie : Movie

        fun bind(movie : Movie) {
            this.movie = movie

            val posterImageView = itemView.findViewById<ImageView>(R.id.poster_image_view)

            Utils.bindImage(movie.poster, posterImageView, R.drawable.ic_broken_image)

            if (tag == MOVIE_LIST) {
                itemView.findViewById<TextView>(R.id.name_text_view).text = movie.title
                itemView.findViewById<TextView>(R.id.year_text_view).text = Utils.getReleaseYear(movie.releaseDate ?: "")
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (absoluteAdapterPosition != RecyclerView.NO_POSITION)
                listener.onMovieClick(movie)
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