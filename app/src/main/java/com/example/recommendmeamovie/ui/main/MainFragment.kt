package com.example.recommendmeamovie.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.NavigationDirections
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.adapter.MovieAdapter
import com.example.recommendmeamovie.databinding.MainFragmentBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.EventObserver
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment), MovieAdapter.OnMovieClickListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MainFragmentBinding.bind(view).apply {
            btRecommend.setOnClickListener {
                viewModel.navigateToRecommend()
            }

            rvPopular.adapter = MovieAdapter(this@MainFragment, MovieAdapter.MAIN_LIST)
            rvTopRated.adapter = MovieAdapter(this@MainFragment, MovieAdapter.MAIN_LIST)
        }

        subscribeObservers()
        setHasOptionsMenu(true)
    }

    private fun subscribeObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner) { movies ->
            binding.apply {
                tvPopular.isVisible = movies !is Resource.Loading
                (rvPopular.adapter as MovieAdapter).submitList(movies.data)
            }
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) { movies ->
            binding.apply {
                tvTopRated.isVisible = movies !is Resource.Loading
                (rvTopRated.adapter as MovieAdapter).submitList(movies.data)
            }
        }

        viewModel.eventNavigateToRecommend.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToRecommendFragment()
            )
        })

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToMovieFragment(
                    it.id,
                    it.title
                )
            )
        })
    }

    override fun onMovieClick(movie: Movie) {
        viewModel.navigateToMovie(movie)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search_click)
            findNavController().navigate(NavigationDirections.actionGlobalMovieListFragment())
        return true
    }
}