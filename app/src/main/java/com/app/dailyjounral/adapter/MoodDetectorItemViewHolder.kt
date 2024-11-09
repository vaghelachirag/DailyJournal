package com.app.dailyjounral.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.databinding.ItemMoodBinding
import com.app.dailyjounral.databinding.LayoutMoodDetectorBinding
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.model.getMoodResponse.SetSelectedMoodData

class MoodDetectorItemViewHolder(val binding: LayoutMoodDetectorBinding) :  RecyclerView.ViewHolder(binding.root)  {

    fun bind(data: SetSelectedMoodData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }
}