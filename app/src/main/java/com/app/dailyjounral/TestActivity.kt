package com.app.dailyjounral

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.dailyjounral.databinding.TestActivityBinding
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.model.getGratitudeResponse.SaveGratitudeData
import com.app.dailyjounral.view.dialougs.DialogAddGratitude


class TestActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: TestActivityBinding
    private var moodDataList = mutableListOf<MoodDataModel>()


    private var addGratitude = mutableListOf<SaveGratitudeData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addSleepData()
    }


    private fun addSleepData() {
        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_number_one))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_number_two))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_number_three))


        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_number_four))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_number_five))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_number_six))


        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL)
        binding.rvMoodDetector.setLayoutManager(staggeredGridLayoutManager);


        binding.btnAddGratitude.setOnClickListener {
            DialogAddGratitude(this, true, "").setListener(object :
                DialogAddGratitude.OkButtonListener {
                override fun onOkPressed(dialogAddGratitude: DialogAddGratitude, gratitude: String?) {

                    dialogAddGratitude.dismiss()
                }
            }).show()
        }

    }
}