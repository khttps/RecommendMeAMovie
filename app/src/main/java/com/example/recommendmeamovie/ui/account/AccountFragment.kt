package com.example.recommendmeamovie.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.FragmentAccountBinding
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private val viewModel: AccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAccountBinding.bind(view)

        viewModel.sessionId.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                viewModel.getAccount(it)
            }
        }

        viewModel.account.observe(viewLifecycleOwner) {
            it?.let {
                binding.account.text = it.toString()
            }
        }
    }

}