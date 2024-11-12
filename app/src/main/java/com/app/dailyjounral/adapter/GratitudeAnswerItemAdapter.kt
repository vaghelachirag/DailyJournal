package com.app.dailyjounral.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.DetailActivityBinding
import com.app.dailyjounral.databinding.ItemGratitudeBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.getGratitudeResponse.SaveGratitudeData
import com.app.dailyjounral.viewmodel.DetailViewModel


class GratitudeAnswerItemAdapter(
    val context: Context,
    private val list: MutableList<SaveGratitudeData>,
    val binding: DetailActivityBinding,
    val detailViewModel: DetailViewModel,
    val onItemSelected: OnItemSelected<SaveGratitudeData>
) :  RecyclerView.Adapter<GratitudeAnswerItemViewHolder>() {

    private var mSelectedItem: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GratitudeAnswerItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<ItemGratitudeBinding>(
            layoutInflater,
            R.layout.item_gratitude,
            parent,
            false
        )
        return GratitudeAnswerItemViewHolder(binder)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: GratitudeAnswerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])

        holder.binding.ivMenu.setOnClickListener {
            val menu = PopupMenu(context,  holder.binding.ivMenu)
            menu.menu.add(Menu.NONE, 1, 1, "Edit")
            menu.menu.add(Menu.NONE, 2, 2, "Delete")
            menu.show()

            menu.setOnMenuItemClickListener { item ->
                val i = item.itemId
                when (i) {
                    1 -> {
                        Log.e("Edit","Edit")
                         holder.binding.edtGratitudeAns.focusable
                        //handle share
                        true
                    }
                    2 -> {
                        detailViewModel.deleteGratitudeData(position)
                        Log.e("Delete","Delete")
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

        }
    }


    override fun getItemCount(): Int {
        return list.size
    }
}