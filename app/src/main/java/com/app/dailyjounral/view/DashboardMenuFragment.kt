package com.app.dailyjounral.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.MoodAdapter
import com.app.dailyjounral.databinding.DashboardMenuFragmentBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.view.base.menu.DashboardActivity
import com.app.dailyjounral.viewmodel.DashboardMenuViewModel

class DashboardMenuFragment: BaseFragment()  {

    private var _binding: DashboardMenuFragmentBinding? = null

    private val binding get() = _binding!!

    private val dashboardMenuViewModel by lazy { DashboardMenuViewModel(activity as Context,binding,this@DashboardMenuFragment) }

    private var moodDataList = mutableListOf<MoodDataModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DashboardMenuFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = dashboardMenuViewModel
        binding.lifecycleOwner = this
        dashboardMenuViewModel.init()

        dashboardMenuViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        addMoodData()
        setAction()
        setBottomTab()
        return binding.root
    }

    private fun setBottomTab() {
       /* binding.txtHome.setTextColor(resources.getColor(R.color.tab_selected_bg))
        binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_un_selected_bg))

        binding.llTab1.visibility  = View.VISIBLE
        binding.llTab2.visibility  = View.GONE*/
    }

    private fun setAction() {
         binding.cardTipOfTheDay.setOnClickListener {
            /* AppConstants.detailType = 1
             val iDashboard = Intent(activity, DetailActivity::class.java)
             iDashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
             iDashboard.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
             startActivity(iDashboard)*/
             AppConstants.detailType = 1
             (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
             (context as DashboardActivity).setSelectedMenuPosition(1)
         }

        binding.cardDailyQuote.setOnClickListener {
            AppConstants.detailType = 2
            (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
            (context as DashboardActivity).setSelectedMenuPosition(2)
        }

        binding.cardDailyGeneral.setOnClickListener {
            AppConstants.detailType = 3
            (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
            (context as DashboardActivity).setSelectedMenuPosition(3)
        }
/*
        binding.llHome.setOnClickListener {
            binding.llTab1.visibility = View.VISIBLE
            binding.llTab2.visibility = View.GONE


            binding.txtHome.setTextColor(resources.getColor(R.color.tab_selected_bg))
            binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_un_selected_bg))

            binding.ivHome.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY)
            binding.ivAnalytics.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_un_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY)
        }

        binding.llAnalystic.setOnClickListener {
            binding.llTab1.visibility = View.GONE
            binding.llTab2.visibility = View.VISIBLE

            binding.txtHome.setTextColor(resources.getColor(R.color.tab_un_selected_bg))
            binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_selected_bg))

            binding.ivHome.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_un_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY)
            binding.ivAnalytics.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY)
        }*/
    }

    private fun addMoodData() {
        moodDataList = mutableListOf()
        moodDataList.add(MoodDataModel("Sleep","",R.drawable.list_sleep_icon))
        moodDataList.add(MoodDataModel("Gratitude","",R.drawable.list_graditity_icon))
        moodDataList.add(MoodDataModel("Mood","",R.drawable.list_mood_icon))


        moodDataList.add(MoodDataModel("Goal Setting","",R.drawable.list_goal_setting))
        moodDataList.add(MoodDataModel("Work-out","",R.drawable.list_icon_weight))
        moodDataList.add(MoodDataModel("Self-Care Tip","",R.drawable.list_selfcare_icon))

        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvSleep.layoutManager = layoutManager
        binding.rvSleep.adapter = MoodAdapter(requireActivity(), moodDataList,object :
            OnItemSelected<MoodDataModel> {

            override fun onItemSelected(t: MoodDataModel?, position: Int) {
                //  clickMenuEvent(t)
                Log.e("Postition",position.toString())

                if (position == 0){
                    AppConstants.detailType = 4
                    (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
                    (context as DashboardActivity).setSelectedMenuPosition(4)
                }

                if (position == 1){
                    AppConstants.detailType = 5
                    (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
                    (context as DashboardActivity).setSelectedMenuPosition(5)
                }
                if (position == 2){
                    AppConstants.detailType = 6
                    (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
                    (context as DashboardActivity).setSelectedMenuPosition(6)
                }

                if (position == 3){
                    AppConstants.detailType = 7
                    (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
                    (context as DashboardActivity).setSelectedMenuPosition(7)
                }

                if (position == 4){
                    AppConstants.detailType = 8
                    (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
                    (context as DashboardActivity).setSelectedMenuPosition(8)
                }
                if (position == 5){
                    AppConstants.detailType = 9
                    (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
                    (context as DashboardActivity).setSelectedMenuPosition(9)
                }
                if (position == 6){
                    AppConstants.detailType = 10
                    (activity as DashboardActivity).navController.navigate(R.id.detailViewFragment)
                    (context as DashboardActivity).setSelectedMenuPosition(10)
                }

            }

        })

    }
}