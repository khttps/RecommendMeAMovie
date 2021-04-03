package com.example.recommendmeamovie.ui.movie

import android.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.MovieFragmentBinding

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = MovieFragmentBinding.inflate(inflater)
        val args: MovieFragmentArgs by navArgs()

        findNavController().currentDestination?.label = args.movieName

        val viewModelFactory = MovieViewModelFactory(args.movieId)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.castList.adapter = CastAdapter()
        binding.crewList.adapter = CrewAdapter()

        return binding.root
    }


}