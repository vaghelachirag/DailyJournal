package com.app.dailyjounral.adapter

import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.databinding.LayoutMenuItemBinding
import com.app.dailyjounral.model.MenuDataModel


class MenuItemViewHolder(val binding: LayoutMenuItemBinding) :  RecyclerView.ViewHolder(binding.root) {
    fun bind(data: MenuDataModel) {
        binding.position = adapterPosition
        binding.holder = this
        binding.itemData = data
    }

}