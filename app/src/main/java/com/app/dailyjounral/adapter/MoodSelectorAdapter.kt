package com.app.dailyjounral.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.LayoutMoodDetectorBinding
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.interfaces.OnItemSelected
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MoodSelectorAdapter(val context: Context, private val list: MutableList<MoodDataModel>, val onItemSelected: OnItemSelected<MoodDataModel>):  RecyclerView.Adapter<MoodDetectorItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodDetectorItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<LayoutMoodDetectorBinding>(
            layoutInflater,
            R.layout.layout_mood_detector,
            parent,
            false
        )
        return MoodDetectorItemViewHolder(binder)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoodDetectorItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context).load(list[position].image).apply(options).into(holder.binding.ivIcon)
    }
    override fun getItemCount(): Int {
        return list.size
    }
}