package com.example.recommendmeamovie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.adapter.CreditsAdapter
import com.example.recommendmeamovie.databinding.MovieFragmentBinding
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.movie_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MovieFragmentBinding.bind(view)

        val movieViewModel: MovieViewModel by viewModels()
        val args: MovieFragmentArgs by navArgs()

        (activity as MainActivity).supportActionBar?.title = args.movieName

        binding.apply {
            viewModel = movieViewModel
            lifecycleOwner = this@MovieFragment

            castList.adapter = CreditsAdapter()
            crewList.adapter = CreditsAdapter()

        }
    }

}