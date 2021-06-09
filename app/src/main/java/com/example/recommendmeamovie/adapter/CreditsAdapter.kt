package com.example.recommendmeamovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.databinding.ListItemCreditsBinding
import com.example.recommendmeamovie.domain.Credit


class CreditsAdapter : ListAdapter<Credit, CreditsAdapter.CreditViewHolder>(ListItemCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CreditViewHolder(ListItemCreditsBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val credit = getItem(position)
        holder.bind(credit)
    }

    class CreditViewHolder(val binding: ListItemCreditsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(credit : Credit) {
            binding.credit = credit
        }
    }

    class ListItemCallbacks : DiffUtil.ItemCallback<Credit>() {
        override fun areItemsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem == newItem
        }

    }

}