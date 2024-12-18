package com.app.dailyjounral.viewmodel.analystic

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.adapter.GeneralGratitudeAnswerAdapter
import com.app.dailyjounral.databinding.FragmentGeneralEntryBinding
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.view.fragment.GeneralEntryFragment


class GeneralEntryViewModel(val context: Context, val binding: FragmentGeneralEntryBinding, generalEntryFragment: GeneralEntryFragment) : BaseViewModel()  {

    private var gratitudeList = ArrayList<String>()

    fun init() {
        addGratitudeAnswerList()
        setAdapter()
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvGratitude.layoutManager = layoutManager
        binding.rvGratitude.adapter = GeneralGratitudeAnswerAdapter(context,gratitudeList)
    }

    private fun addGratitudeAnswerList() {
        gratitudeList.add("Good morning")
        gratitudeList.add("Good morning")
        gratitudeList.add("Good morning")
        gratitudeList.add("Good morning")
    }

}