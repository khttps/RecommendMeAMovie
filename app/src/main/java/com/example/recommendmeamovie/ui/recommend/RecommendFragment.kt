package com.example.recommendmeamovie.ui.recommend

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.RecommendFragmentBinding
import com.example.recommendmeamovie.network.MovieDetails
import com.example.recommendmeamovie.network.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendFragment : Fragment() {

    private lateinit var viewModel: RecommendViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = RecommendFragmentBinding.inflate(inflater)

        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch {
            withContext(Dispatchers.IO) {
                val details = MovieService.retrofitService.getMovieDetails(34433, BuildConfig.API_KEY)

                withContext(Dispatchers.Main) {
                    binding.recommendTextView.text = details.title
                }

            }
        }

        //viewModel = ViewModelProvider(this).get(RecommendViewModel::class.java)
        //binding.viewModel = viewModel

        return binding.root
    }

}