package com.app.dailyjounral

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.adapter.MoodAdapter
import com.app.dailyjounral.databinding.ActivityMainBinding
import com.app.dailyjounral.model.MoodDataModel
import com.app.secureglobal.interfaces.OnItemSelected


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var moodDataList = mutableListOf<MoodDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addMoodData()
    }

    private fun addMoodData() {
        moodDataList.add(MoodDataModel("Sleep","",R.drawable.sleep_icon))
        moodDataList.add(MoodDataModel("Gratitude","",R.drawable.gradidute_icon))
        moodDataList.add(MoodDataModel("Mood","",R.drawable.mood_icon))


        moodDataList.add(MoodDataModel("Sleep","",R.drawable.sleep_icon))
        moodDataList.add(MoodDataModel("Gratitude","",R.drawable.gradidute_icon))
        moodDataList.add(MoodDataModel("Mood","",R.drawable.mood_icon))

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvSleep.layoutManager = layoutManager

        binding.rvSleep.adapter = MoodAdapter(this, moodDataList,object :
            OnItemSelected<MoodDataModel> {

            override fun onItemSelected(t: MoodDataModel?, position: Int) {
              //  clickMenuEvent(t)
            }

        })
    }
}