package com.app.dailyjounral.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.MenuItemViewHolder
import com.app.dailyjounral.databinding.LayoutMenuItemBinding
import com.app.dailyjounral.model.MenuDataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.app.dailyjounral.interfaces.OnItemSelected


class MenuItemAdapter(val context: Context, private val list: MutableList<MenuDataModel>, val onItemSelected: OnItemSelected<MenuDataModel>) :  RecyclerView.Adapter<MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<LayoutMenuItemBinding>(
            layoutInflater,
            R.layout.layout_menu_item,
            parent,
            false
        )
        return MenuItemViewHolder(binder)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MenuItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context).load(list[position].image).apply(options).into(holder.binding.ivLogo)
        holder.binding.txtMenuName.text = list[position].title

        if (list[position].isSelected){
            holder.binding.llMain.background = context.getDrawable(R.drawable.slide_menu_selected_bg)
        }else{
            holder.binding.llMain.background =  null
        }

        holder.binding.llMain.setOnClickListener {
            list[position].isSelected = true
            notifyDataSetChanged()
            onItemSelected.onItemSelected(list[position], position)
        }

    }


    override fun getItemCount(): Int {
        return list.size
    }
}