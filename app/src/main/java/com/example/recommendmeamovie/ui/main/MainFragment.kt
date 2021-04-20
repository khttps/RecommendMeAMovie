package com.example.recommendmeamovie.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.adapters.MovieAdapter
import com.example.recommendmeamovie.databinding.MainFragmentBinding
import com.example.recommendmeamovie.domain.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = MainFragmentBinding.inflate(inflater)

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
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToMovieFragment(it.id, it.title))
                viewModel.navigateToMovieCompleted()
            }
        })

        return binding.root
    }

    override fun onMovieClick(movie: Movie) {
        viewModel.navigateToMovie(movie)
    }


}