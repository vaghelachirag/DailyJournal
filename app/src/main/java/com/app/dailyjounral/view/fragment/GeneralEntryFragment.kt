package com.app.dailyjounral.view.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.FragmentGeneralEntryBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.analystic.GeneralEntryViewModel
import java.util.Calendar


class GeneralEntryFragment: BaseFragment() {
    private var binding: FragmentGeneralEntryBinding? = null
    private val generalViewModel by lazy { GeneralEntryViewModel(activity as Context,binding!!,this@GeneralEntryFragment) }
    private val myCalendar: Calendar = Calendar.getInstance()
    private var dateListener : OnDateSetListener? = null
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGeneralEntryBinding.inflate(inflater, container, false)
        binding!!.viewModel = generalViewModel
        binding!!.lifecycleOwner = this

        showCalendar()
        DatePickerDialog(
            requireActivity(), dateListener,
            myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
        ).show()
        return binding!!.root

    }

    private fun showCalendar() {
        dateListener = OnDateSetListener { _, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
              //  updateLabel()
            }
    }
}