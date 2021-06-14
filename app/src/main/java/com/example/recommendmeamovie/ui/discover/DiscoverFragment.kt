package com.example.recommendmeamovie.ui.discover

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.NavigationDirections
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.adapter.MoviePagingAdapter
import com.example.recommendmeamovie.adapter.OnMovieClickListener
import com.example.recommendmeamovie.databinding.FragmentDiscoverBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment(R.layout.fragment_discover), OnMovieClickListener {

    private val viewModel: DiscoverViewModel by viewModels()

    private var _binding : FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDiscoverBinding.bind(view)

        (activity as MainActivity).supportActionBar?.title = "Discover"

        binding.apply {
            recommendFab.setOnClickListener {
                viewModel.navigateToRecommend()
            }

            popularRecyclerView.apply {
                setHasFixedSize(true)
                adapter = MoviePagingAdapter(
                    this@DiscoverFragment,
                    R.layout.list_item_discover
                )
            }

            topRatedRecyclerView.apply {
                setHasFixedSize(true)
                adapter = MoviePagingAdapter(
                    this@DiscoverFragment,
                    R.layout.list_item_discover
                )
            }
        }

        subscribeObservers()
        setHasOptionsMenu(true)
    }

    private fun subscribeObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner) { movies ->
            binding.apply {
                (popularRecyclerView.adapter as MoviePagingAdapter)
                    .submitData(viewLifecycleOwner.lifecycle, movies)
            }
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) { movies ->
            binding.apply {
                (topRatedRecyclerView.adapter as MoviePagingAdapter)
                    .submitData(viewLifecycleOwner.lifecycle, movies)
            }
        }

        viewModel.eventNavigateToRecommend.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(DiscoverFragmentDirections.actionDiscoverFragmentToRecommendFragment())
        })

        viewModel.eventNavigateToSearch.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(NavigationDirections.actionGlobalMovieListFragment())
        })

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                DiscoverFragmentDirections.actionDiscoverFragmentToMovieDetailsFragment(it.id, it.title)
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
            viewModel.navigateToSearch()

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}