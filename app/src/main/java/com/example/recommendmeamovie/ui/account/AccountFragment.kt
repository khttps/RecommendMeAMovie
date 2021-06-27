package com.example.recommendmeamovie.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.adapter.MovieAdapter
import com.example.recommendmeamovie.databinding.FragmentAccountBinding
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.util.Constants.IMAGE_URL
import com.example.recommendmeamovie.util.EventObserver
import com.example.recommendmeamovie.util.Resource
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private val viewModel: AccountViewModel by viewModels()
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAccountBinding.bind(view)

        subscribeObservers()

        binding.logOutButton.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun subscribeObservers() {
        viewModel.account.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                Glide.with(this@AccountFragment)
                    .load(IMAGE_URL + it.data!!.avatar)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_placeholder)
                    .into(binding.avatar)

                binding.name.text = it.data.name
                it.data.username.apply {
                    (activity as MainActivity).supportActionBar?.title = this
                    binding.username.text = this
                }
            }
        }

        viewModel.favorites.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                val adapter = MovieAdapter()
                binding.favoritesRecyclerView.adapter = adapter
                (binding.favoritesRecyclerView.adapter as MovieAdapter).submitList(it.data)

            } else {
                Timber.tag("account-debug").d(it.error)
            }
        }

        viewModel.watchlist.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                val adapter = MovieAdapter()
                binding.favoritesRecyclerView.adapter = adapter
                (binding.favoritesRecyclerView.adapter as MovieAdapter).submitList(it.data)

            } else {
                Timber.tag("account-debug").d(it.error)
            }
        }

        viewModel.eventNavigateUp.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })

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
            }.show()
    }

}