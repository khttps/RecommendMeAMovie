package com.example.recommendmeamovie.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recommendmeamovie.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = MainFragmentBinding.inflate(inflater)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.popularList.adapter = MovieAdapter()
        binding.topRatedList.adapter = MovieAdapter()

        viewModel.eventNavigateToRecommend.observe(this.viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToRecommendFragment())
                viewModel.navigateToRecommendCompleted()
            }
        })

        return binding.root
    }

}