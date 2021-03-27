package com.example.recommendmeamovie.ui.watchlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.WatchlistFragmentBinding

class WatchlistFragment : Fragment() {

    private lateinit var viewModel: WatchlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = WatchlistFragmentBinding.inflate(inflater)

        return binding.root
    }


}