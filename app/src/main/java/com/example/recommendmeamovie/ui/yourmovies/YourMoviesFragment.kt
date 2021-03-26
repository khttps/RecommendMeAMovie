package com.example.recommendmeamovie.ui.yourmovies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recommendmeamovie.R

class YourMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = YourMoviesFragment()
    }

    private lateinit var viewModel: YourMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.your_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(YourMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}