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
import com.example.recommendmeamovie.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendFragment : Fragment() {

    private lateinit var viewModel: RecommendViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = RecommendFragmentBinding.inflate(inflater)

        val call = MovieService.retrofitService.getPopularMovies(BuildConfig.API_KEY)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                binding.recommendTextView.text = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                binding.recommendTextView.text = t.message
            }

        })

        //viewModel = ViewModelProvider(this).get(RecommendViewModel::class.java)
        //binding.viewModel = viewModel

        return binding.root
    }

}