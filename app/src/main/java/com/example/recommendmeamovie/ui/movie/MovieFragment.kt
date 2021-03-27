package com.example.recommendmeamovie.ui.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.MovieFragmentBinding

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = MovieFragmentBinding.inflate(inflater)

        return binding.root
    }

}