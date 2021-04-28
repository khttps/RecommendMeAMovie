package com.example.recommendmeamovie.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.NavigationDirections
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.adapter.MovieAdapter
import com.example.recommendmeamovie.databinding.MainFragmentBinding
import com.example.recommendmeamovie.domain.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment), MovieAdapter.OnMovieClickListener {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MainFragmentBinding.bind(view)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.popularList.adapter = MovieAdapter(this, MovieAdapter.MAIN_LIST)
        binding.topRatedList.adapter = MovieAdapter(this, MovieAdapter.MAIN_LIST)

        viewModel.eventNavigateToRecommend.observe(this.viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToRecommendFragment())
                viewModel.navigateToRecommendCompleted()
            }
        })

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, {
            if (it != null) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToMovieFragment(
                        it.id,
                        it.title
                    )
                )
                viewModel.navigateToMovieCompleted()
            }
        })

        setHasOptionsMenu(true)
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