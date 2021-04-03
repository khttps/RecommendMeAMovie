package com.example.recommendmeamovie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.databinding.CreditsListItemBinding
import com.example.recommendmeamovie.network.Cast



class CastAdapter() : ListAdapter<Cast, CastAdapter.CastViewHolder>(ListItemCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CastViewHolder(CreditsListItemBinding.inflate(inflater))

    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = getItem(position)
        holder.bind(cast)
    }


    class CastViewHolder(val binding: CreditsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cast : Cast) {
            binding.creditsRole.text = cast.character
            binding.creditsName.text = cast.name
            binding.picture = cast.picture

        }

    }

    class ListItemCallbacks : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

    }

}