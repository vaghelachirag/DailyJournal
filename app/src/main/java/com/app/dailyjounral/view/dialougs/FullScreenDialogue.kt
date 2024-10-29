package com.app.dailyjounral.view.dialougs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.WeekAdapter
import com.app.dailyjounral.databinding.FullScreenDialougBinding
import com.app.dailyjounral.model.ModelDataWeek
import com.app.dailyjounral.interfaces.OnItemSelected


class FullScreenDialogue(private var mContext: Context) : Dialog(mContext, R.style.ThemeDialog)  {


    private lateinit var binding: FullScreenDialougBinding

    private var weekDayList = mutableListOf<ModelDataWeek>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.full_screen_dialoug, null, false)

        binding.rvWeekDays

        addWeekData()

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.gravity = Gravity.CENTER

        window!!.setAttributes(lp)


        setContentView(binding.root)

    }

    private fun addWeekData() {
        weekDayList.add(ModelDataWeek("S",false))
        weekDayList.add(ModelDataWeek("M",true))
        weekDayList.add(ModelDataWeek("T",false))
        weekDayList.add(ModelDataWeek("W",false))
        weekDayList.add(ModelDataWeek("T",false))
        weekDayList.add(ModelDataWeek("S",false))

        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvWeekDays.layoutManager = layoutManager

        binding.rvWeekDays.adapter = WeekAdapter(context, weekDayList,object :
            OnItemSelected<ModelDataWeek> {

            override fun onItemSelected(t: ModelDataWeek?, position: Int) {
                //  clickMenuEvent(t)
            }

        })

    }

}