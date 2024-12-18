package com.app.dailyjounral.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.R
import io.reactivex.annotations.NonNull


class GeneralGratitudeAnswerAdapter(val context: Context, private var listAnswer: ArrayList<String>) :
    RecyclerView.Adapter<GeneralGratitudeAnswerAdapter.TagsViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): TagsViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_general_entry, parent, false)
        return TagsViewHolder(layoutView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(@NonNull holder: TagsViewHolder, position: Int) {
        var pos = position + 1
        holder.txtAnswer.text = context.resources.getString(R.string.text_with_bullet) +  " "+ listAnswer[position]
    }

    override fun getItemCount(): Int {
        Log.e("Answer",listAnswer.size.toString())
        return listAnswer.size
    }

    inner class TagsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtAnswer: TextView = view.findViewById<TextView>(R.id.txtAnswer)
    }

}