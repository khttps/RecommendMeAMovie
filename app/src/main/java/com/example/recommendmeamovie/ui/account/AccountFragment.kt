package com.example.recommendmeamovie.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.FragmentAccountBinding
import com.example.recommendmeamovie.util.Constants.IMAGE_URL
import com.example.recommendmeamovie.util.Resource
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private val viewModel: AccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAccountBinding.bind(view)

        viewModel.account.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                binding.apply {
                    name.text = it.data!!.name
                    username.text = it.data.username

                    Glide.with(this.root).load(IMAGE_URL + it.data.avatar).circleCrop().into(avatar)
                }
            }
        }

        binding.logOutButton.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(this@AccountFragment.requireContext())
            .setTitle(resources.getString(R.string.dialog_title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNegativeButton(resources.getString(R.string.dialog_decline)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.dialog_accept)) { dialog, _ ->
                viewModel.clearSession()
                dialog.dismiss()
                findNavController().navigateUp()
            }.show()
    }

}