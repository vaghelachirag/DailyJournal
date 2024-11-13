package com.app.dailyjounral.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.databinding.ItemGratitudeBinding
import com.app.dailyjounral.model.MenuDataModel
import com.app.dailyjounral.model.getGratitudeResponse.GetGratitudeListData
import com.app.dailyjounral.model.getGratitudeResponse.SaveGratitudeData

class GratitudeAnswerItemViewHolder(val binding: ItemGratitudeBinding) :  RecyclerView.ViewHolder(binding.root) {
    fun bind(data: GetGratitudeListData) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }
}