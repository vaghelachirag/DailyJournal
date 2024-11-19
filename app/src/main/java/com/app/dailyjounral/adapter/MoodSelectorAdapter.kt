package com.app.dailyjounral.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.LayoutMoodDetectorBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.getMoodResponse.SetSelectedMoodData
import com.app.dailyjounral.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MoodSelectorAdapter(
    val context: Context,
    private val list: MutableList<SetSelectedMoodData>,
    private val selectedMoodTypeId: MutableLiveData<Int>,
    private val detailViewModel: DetailViewModel,
    private var firstTime: Boolean,
    val onItemSelected: OnItemSelected<SetSelectedMoodData>
):  RecyclerView.Adapter<MoodDetectorItemViewHolder>() {

    private var mSelectedPosition = -1

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

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MoodDetectorItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])

        holder.binding.llMood.visibility = View.VISIBLE
        holder.binding.ivIcon.visibility = View.GONE

      //  Log.e("SelectedMoodId",selectedMoodTypeId.value.toString())

       val options: RequestOptions = RequestOptions()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context).load(list[position].image).apply(options).into(holder.binding.ivMood)

        if (list[position].typeId == selectedMoodTypeId.value){
            holder.binding.rbSelection.isChecked = true
        }
        else{
            holder.binding.rbSelection.isChecked = false
        }

        if (mSelectedPosition == position){
            holder.binding.rbSelection.isChecked = true
        }else{
            if (!firstTime){
                holder.binding.rbSelection.isChecked = false
            }
        }

        holder.binding.rbSelection.setOnClickListener {
            detailViewModel.saveMoodApiResponse(list[position].typeId)
            mSelectedPosition = position
            firstTime = false
            notifyDataSetChanged()
        }

        holder.binding.txtLabel.text = list[position].title

    }
    override fun getItemCount(): Int {
        return list.size
    }
}