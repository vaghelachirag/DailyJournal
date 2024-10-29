package com.app.dailyjounral.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ItemWeekDaysBinding
import com.app.dailyjounral.model.ModelDataWeek
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.interfaces.OnItemSelected

class WeekAdapter(val context: Context, private val list: MutableList<ModelDataWeek>, val onItemSelected: OnItemSelected<ModelDataWeek>) :  RecyclerView.Adapter<WeekItemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<ItemWeekDaysBinding>(
            layoutInflater,
            R.layout.item_week_days,
            parent,
            false
        )
        return WeekItemViewHolder(binder)
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: WeekItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])

        holder.binding.txtWeekDays.text = list[position].title

        if (Utils().getCurrentWeekDay() == position + 1){
           holder.binding.txtWeekDays.setTextColor(R.color.red)
        }
        else{
            holder.binding.txtWeekDays.setTextColor(R.color.black)
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
}