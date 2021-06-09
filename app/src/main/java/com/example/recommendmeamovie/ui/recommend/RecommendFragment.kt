package com.example.recommendmeamovie.ui.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.FragmentRecommendBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendFragment : Fragment(R.layout.fragment_recommend) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRecommendBinding.bind(view)
        val viewModel: RecommendViewModel by viewModels()

    }

}


