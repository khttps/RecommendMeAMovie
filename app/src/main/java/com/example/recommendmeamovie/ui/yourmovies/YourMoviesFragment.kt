package com.example.recommendmeamovie.ui.yourmovies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.YourMoviesFragmentBinding

class YourMoviesFragment : Fragment() {

    private lateinit var viewModel: YourMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding =  YourMoviesFragmentBinding.inflate(inflater)

        return binding.root
    }

}