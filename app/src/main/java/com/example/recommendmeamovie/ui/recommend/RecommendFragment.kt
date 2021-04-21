package com.example.recommendmeamovie.ui.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.RecommendFragmentBinding

data class Preference(
    val question: String,
    val choices: Map<String, String>
)

class RecommendFragment : Fragment(R.layout.recommend_fragment) {

    private lateinit var viewModel: RecommendViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = RecommendFragmentBinding.bind(view)


    }

}


