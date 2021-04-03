package com.example.recommendmeamovie.ui.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recommendmeamovie.databinding.MovieListFragmentBinding

class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = MovieListFragmentBinding.inflate(inflater)

        return binding.root
    }


}