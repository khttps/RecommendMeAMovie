package com.example.recommendmeamovie.ui.movielist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.MovieListFragmentBinding
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.adapter.MovieAdapter
import com.example.recommendmeamovie.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.movie_list_fragment), MovieAdapter.OnMovieClickListener {

    private val viewModel : MovieListViewModel by viewModels()

    override fun setArguments(args: Bundle?) {
        super.setArguments(Bundle(args)
            .apply { putBundle("args", args) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MovieListFragmentBinding.bind(view)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.list.adapter = MovieAdapter(this, MovieAdapter.MOVIE_LIST)

        viewModel.eventNavigateToMovie.observe(viewLifecycleOwner, {
            if (it != null) {
                findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieFragment(it.id, it.title))
                viewModel.navigateToMovieCompleted()
            }
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

                viewModel.executeQuery(query)
                return true
            }
        })

        searchView.setOnCloseListener {
            findNavController().navigateUp()
            return@setOnCloseListener true
        }

    }

}