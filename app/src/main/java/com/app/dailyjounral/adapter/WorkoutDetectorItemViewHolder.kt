package com.app.dailyjounral.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.databinding.LayoutWorkoutDetectorBinding
import com.app.dailyjounral.model.getWorkoutDataResponse.SetSelectedWorkoutData

class WorkoutDetectorItemViewHolder(val binding: LayoutWorkoutDetectorBinding) :  RecyclerView.ViewHolder(binding.root)   {

    fun bind(data: SetSelectedWorkoutData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }
}