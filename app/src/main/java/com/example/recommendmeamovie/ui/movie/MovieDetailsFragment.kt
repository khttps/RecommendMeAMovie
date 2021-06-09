package com.example.recommendmeamovie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.adapter.CreditsAdapter
import com.example.recommendmeamovie.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailsBinding.bind(view)

        val viewModel: MovieDetailsViewModel by viewModels()
        val args: MovieDetailsFragmentArgs by navArgs()

        (activity as MainActivity).supportActionBar?.title = args.movieName

        binding.apply {
            this.viewModel = viewModel
            lifecycleOwner = this@MovieDetailsFragment

            castList.adapter = CreditsAdapter()
            crewList.adapter = CreditsAdapter()
        }
    }

}