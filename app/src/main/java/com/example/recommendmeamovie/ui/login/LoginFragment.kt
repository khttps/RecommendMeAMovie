package com.example.recommendmeamovie.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.FragmentLoginBinding
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)
        val viewModel: LoginViewModel by viewModels()

        binding.loginButton.setOnClickListener {
            viewModel.getRequestToken()
        }

        viewModel.token.observe(viewLifecycleOwner)  {
            if (it is Resource.Success) {
                val intent = Intent(Intent.ACTION_VIEW, viewModel.getAuthUrl(it.data!!))
                startActivity(intent)
            }
        }
    }
}