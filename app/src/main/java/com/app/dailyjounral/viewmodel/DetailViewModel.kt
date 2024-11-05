package com.app.dailyjounral.viewmodel

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.MoodSelectorAdapter
import com.app.dailyjounral.adapter.WeekAdapter
import com.app.dailyjounral.databinding.DetailActivityBinding
import com.app.dailyjounral.model.ModelDataWeek
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.uttils.AppConstants
import com.app.secureglobal.model.base.BaseViewModel


class DetailViewModel(val context: Context, val binding: DetailActivityBinding) : BaseViewModel()  {

    private var weekDayList = mutableListOf<ModelDataWeek>()
    private var moodDataList = mutableListOf<MoodDataModel>()

    fun init() {
        if (AppConstants.detailType == 4){
            addSleepData()
        }
        if (AppConstants.detailType == 5){
          //  addWorkoutData()
        }

        if (AppConstants.detailType == 6){
          addMoodData()
        }

        if (AppConstants.detailType == 8){
            addWorkoutData()
        }
       addWeekData()

    }

    private fun addSleepData() {
        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_number_one))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_number_two))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_number_three))


        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_number_four))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_number_five))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.six_icon))
    }

    private fun addWorkoutData() {
        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_running))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_dumbell))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_running))


        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_dumbell))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_running))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_dumbell))
    }

    private fun addMoodData() {
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_menu_sleep_selected))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_menu_sleep_selected))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_menu_sleep_selected))


        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_menu_sleep_selected))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_menu_sleep_selected))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_menu_sleep_selected))

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



        binding.rvMoodDetector.setLayoutManager(GridLayoutManager(context, 3))

        binding.rvMoodDetector.adapter = MoodSelectorAdapter(context, moodDataList,object :
            OnItemSelected<MoodDataModel> {

            override fun onItemSelected(t: MoodDataModel?, position: Int) {
                //  clickMenuEvent(t)
            }

        })

    }

}