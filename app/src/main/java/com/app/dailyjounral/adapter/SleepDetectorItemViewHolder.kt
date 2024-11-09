package com.app.dailyjounral.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.databinding.LayoutSleepDetectorBinding
import com.app.dailyjounral.databinding.LayoutWorkoutDetectorBinding
import com.app.dailyjounral.model.getSleepDataResponse.SetSelectedSleepData
import com.app.dailyjounral.model.getWorkoutDataResponse.SetSelectedWorkoutData

class SleepDetectorItemViewHolder(val binding: LayoutSleepDetectorBinding) :  RecyclerView.ViewHolder(binding.root)  {


    fun bind(data: SetSelectedSleepData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }
}