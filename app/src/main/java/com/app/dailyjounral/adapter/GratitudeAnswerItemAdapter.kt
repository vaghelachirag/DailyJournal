package com.app.dailyjounral.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import com.app.dailyjounral.model.getGratitudeResponse.GetGratitudeListData
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.dialougs.DialogAddGratitude
import com.app.dailyjounral.viewmodel.DetailViewModel


class GratitudeAnswerItemAdapter(val context: Context, private val list: List<GetGratitudeListData>, val binding: DetailActivityBinding, private val detailViewModel: DetailViewModel, val onItemSelected: OnItemSelected<GetGratitudeListData>) :  RecyclerView.Adapter<GratitudeAnswerItemViewHolder>() {


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

        holder.binding.edtGratitudeAns.setText(Utility.getNullToBlankString(list[position].getAnswer()!!))

        holder.binding.ivMenu.setOnClickListener {
            val menu = PopupMenu(context,  holder.binding.ivMenu)
            menu.menu.add(Menu.NONE, 1, 1, "Edit")
            menu.menu.add(Menu.NONE, 2, 2, "Delete")
            menu.show()

            menu.setOnMenuItemClickListener { item ->
                val i = item.itemId
                when (i) {
                    1 -> {
                        Log.e("Edit","Edit" + list[position].getGratitudeUserRecordId())
                         holder.binding.edtGratitudeAns.focusable

                        DialogAddGratitude(context,false,list[position].getAnswer()!!).setListener(object :
                            DialogAddGratitude.OkButtonListener {
                            override fun onOkPressed(dialogAddGratitude: DialogAddGratitude, gratitude: String?) {
                                Log.e("Answer",gratitude.toString())
                                if (!gratitude.isNullOrEmpty()){
                                    detailViewModel.saveGratitudeApiResponse(gratitude,list[position].getGratitudeUserRecordId())
                                    dialogAddGratitude.dismiss()
                                }
                                else{
                                    Utils().showSnackBar(context!!, "Please add gratitude first!", binding.constraintLayout)
                                }
                            }
                        }).show()

                        //handle share
                        true

                    }
                    2 -> {
                        AlertDialog.Builder(context)
                            .setTitle("Alert!")
                            .setMessage("Are you sure you want to delete?").setPositiveButton(android.R.string.yes) { _, _ ->
                                detailViewModel.deleteGratitudeData(position,list[position].getGratitudeUserRecordId())
                            } // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .show()
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