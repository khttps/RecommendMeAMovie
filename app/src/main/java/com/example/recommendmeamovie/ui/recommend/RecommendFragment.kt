package com.example.recommendmeamovie.ui.recommend

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.RecommendFragmentBinding

data class Preference(
    val question: String,
    val choices: Map<String, String>
)

class RecommendFragment : Fragment() {

    private lateinit var viewModel: RecommendViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = RecommendFragmentBinding.inflate(inflater)


        return binding.root
    }

}


