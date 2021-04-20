package com.example.recommendmeamovie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.adapters.CreditsAdapter
import com.example.recommendmeamovie.databinding.MovieFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val navArgs : MovieFragmentArgs by navArgs()

    override fun setArguments(args: Bundle?) {
        super.setArguments(Bundle(args)
            .apply { putBundle("args", args) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = MovieFragmentBinding.inflate(inflater)
        val viewModel: MovieViewModel by viewModels()

        (activity as MainActivity).supportActionBar?.title = navArgs.movieName

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.castList.adapter = CreditsAdapter()
        binding.crewList.adapter = CreditsAdapter()

        return binding.root
    }

}