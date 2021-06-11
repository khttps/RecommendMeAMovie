package com.example.recommendmeamovie.ui.search

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.adapter.MoviePagingAdapter
import com.example.recommendmeamovie.databinding.FragmentSearchBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.EventObserver
import com.example.recommendmeamovie.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), MoviePagingAdapter.OnMovieClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagingAdapter: MoviePagingAdapter

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        pagingAdapter = MoviePagingAdapter(this@SearchFragment, R.layout.list_item_search)
        binding.recyclerView.apply {
            adapter = pagingAdapter
            setHasFixedSize(true)
        }

        subscribeObservers()
        setHasOptionsMenu(true)
    }

    private fun subscribeObservers() {
        viewModel.results.observe(viewLifecycleOwner) {
            pagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, EventObserver { movie ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieFragment(movie.id, movie.title)
            )
        })

        viewModel.eventNavigateUp.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigateUp()
        })
    }

    override fun onMovieClick(movie: Movie) {
        viewModel.navigateToMovie(movie)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)

        (searchItem.actionView as SearchView).apply {
            isIconified = false
            queryHint = getString(R.string.search) + "... "

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

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

            setOnCloseListener {
                viewModel.navigateUp()
                return@setOnCloseListener true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}