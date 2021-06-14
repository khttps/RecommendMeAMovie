package com.example.recommendmeamovie.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.FragmentMovielistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovielistFragment : Fragment(R.layout.fragment_movielist) {

    private var _binding: FragmentMovielistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovielistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovielistBinding.bind(view)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}