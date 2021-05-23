package com.example.recommendmeamovie.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.adapter.MovieAdapter
import com.example.recommendmeamovie.databinding.SearchFragmentBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.EventObserver
import com.example.recommendmeamovie.util.Resource
import com.example.recommendmeamovie.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment), MovieAdapter.OnMovieClickListener {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SearchFragmentBinding.bind(view)

        val movieAdapter = MovieAdapter(this@SearchFragment, MovieAdapter.MOVIE_LIST)
        binding.rvSearchResults.adapter = movieAdapter

        viewModel.listResource.observe(viewLifecycleOwner) { results ->
            binding.pbLoading.isVisible = results is Resource.Loading
            binding.rvSearchResults.isVisible = results is Resource.Success
            binding.tvError.isVisible =
                results is Resource.Error || (results is Resource.Success && results.data.isNullOrEmpty())

            binding.tvError.text = when (results) {
                is Resource.Error -> {
                    results.error?.localizedMessage
                }
                else -> getString(R.string.nothing_to_see_here)
            }

            movieAdapter.submitList(results.data)
        }

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieFragment(
                    it.id,
                    it.title
                )
            )
        })

        setHasOptionsMenu(true)
    }

    override fun onMovieClick(movie: Movie) {
        viewModel.navigateToMovie(movie)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.isIconified = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Utils.hideKeyboard(requireActivity())
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrBlank())
                    return false

                viewModel.getSearchResults(query)
                return true
            }
        })

        searchView.setOnCloseListener {
            findNavController().navigateUp()
            return@setOnCloseListener true
        }

    }

}