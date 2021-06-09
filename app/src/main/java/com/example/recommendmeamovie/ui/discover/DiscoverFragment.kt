package com.example.recommendmeamovie.ui.discover

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
import com.example.recommendmeamovie.databinding.FragmentDiscoverBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.util.EventObserver
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment(R.layout.fragment_discover), MovieAdapter.OnMovieClickListener {

    private val viewModel: DiscoverViewModel by viewModels()
    private lateinit var binding : FragmentDiscoverBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = "Discover"

        binding = FragmentDiscoverBinding.bind(view).apply {
            fabRecommend.setOnClickListener {
                viewModel.navigateToRecommend()
            }

            rvPopular.adapter = MovieAdapter(this@DiscoverFragment, MovieAdapter.MAIN_LIST)
            rvTopRated.adapter = MovieAdapter(this@DiscoverFragment, MovieAdapter.MAIN_LIST)
        }

        subscribeObservers()
        setHasOptionsMenu(true)
    }

    private fun subscribeObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner) { movies ->
            binding.apply {
                (rvPopular.adapter as MovieAdapter).submitList(movies.data)
            }
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) { movies ->
            binding.apply {
                (rvTopRated.adapter as MovieAdapter).submitList(movies.data)
            }
        }

        viewModel.eventNavigateToRecommend.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                DiscoverFragmentDirections.actionDiscoverFragmentToRecommendFragment()
            )
        })

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                DiscoverFragmentDirections.actionDiscoverFragmentToMovieDetailsFragment(
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