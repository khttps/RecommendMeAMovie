package com.example.recommendmeamovie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.adapter.CreditsAdapter
import com.example.recommendmeamovie.databinding.MovieFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.movie_fragment) {
    override fun setArguments(args: Bundle?) {
        super.setArguments(Bundle(args)
            .apply { putBundle("args", args) })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MovieFragmentBinding.bind(view)
        val viewModel: MovieViewModel by viewModels()

        (activity as MainActivity).supportActionBar?.title =
            requireArguments().getString("movieName")

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.castList.adapter = CreditsAdapter()
        binding.crewList.adapter = CreditsAdapter()
    }
}