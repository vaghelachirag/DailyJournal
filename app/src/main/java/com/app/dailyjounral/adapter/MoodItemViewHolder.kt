package com.app.dailyjounral.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.databinding.ItemMoodBinding
import com.app.dailyjounral.model.MoodDataModel


class MoodItemViewHolder(val binding: ItemMoodBinding) :  RecyclerView.ViewHolder(binding.root) {
    fun bind(data: MoodDataModel) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }

}