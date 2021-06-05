package com.example.recommendmeamovie.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.LoginFragmentBinding
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = LoginFragmentBinding.bind(view)

        binding.wvLogin.apply {
            webViewClient = LoginWebViewClient()
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true

        }

        viewModel.token.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                binding.wvLogin.loadUrl(
                    "https://www.themoviedb.org/authenticate/" + it.data!!.requestToken
                )
            }
            else {
                println(it.error?.localizedMessage + "XXX")
            }
        }
    }

}