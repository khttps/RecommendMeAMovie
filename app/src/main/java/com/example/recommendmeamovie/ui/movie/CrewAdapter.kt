package com.example.recommendmeamovie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendmeamovie.databinding.CreditsListItemBinding
import com.example.recommendmeamovie.network.Crew


class CrewAdapter() : ListAdapter<Crew, CrewAdapter.CrewViewHolder>(ListItemCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CrewViewHolder(CreditsListItemBinding.inflate(inflater))

    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val Crew = getItem(position)
        holder.bind(Crew)
    }


    class CrewViewHolder(val binding: CreditsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(crew : Crew) {
            binding.creditsRole.text = crew.job
            binding.creditsName.text = crew.name
            binding.picture = crew.picture

        }

    }

    class ListItemCallbacks : DiffUtil.ItemCallback<Crew>() {
        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem == newItem
        }

    }

}