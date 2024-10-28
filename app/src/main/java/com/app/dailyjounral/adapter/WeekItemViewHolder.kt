package com.app.dailyjounral.adapter

import android.icu.util.Calendar.WeekData
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.databinding.ItemWeekDaysBinding
import com.app.dailyjounral.databinding.LayoutMenuItemBinding
import com.app.dailyjounral.model.MenuDataModel
import com.app.dailyjounral.model.ModelDataWeek

class WeekItemViewHolder(val binding: ItemWeekDaysBinding) :  RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ModelDataWeek) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }
}