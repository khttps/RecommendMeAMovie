package com.example.recommendmeamovie.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.databinding.MainFragmentBinding
import com.example.recommendmeamovie.domain.Movie


class MainFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    lateinit var viewModel : MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = MainFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.popularList.adapter = MovieAdapter(this)
        binding.topRatedList.adapter = MovieAdapter(this)

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