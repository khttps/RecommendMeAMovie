package com.example.recommendmeamovie.ui.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.MovieListFragmentBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.adapters.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    private val viewModel : MovieListViewModel by viewModels()

    override fun setArguments(args: Bundle?) {
        super.setArguments(Bundle(args)
            .apply { putBundle("args", args) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = MovieListFragmentBinding.inflate(inflater)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.list.adapter = MovieAdapter(this, MovieAdapter.MOVIE_LIST)

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, {
            if (it != null) {
                findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieFragment(it.id, it.title))
                viewModel.navigateToMovieCompleted()
            }
        })

        return binding.root
    }

    override fun onMovieClick(movie: Movie) {
        viewModel.navigateToMovie(movie)
    }



}