package com.app.dailyjounral.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.MoodAdapter
import com.app.dailyjounral.databinding.DashboardMenuFragmentBinding
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.view.base.menu.DashboardActivity
import com.app.dailyjounral.view.detail.DetailActivity
import com.app.dailyjounral.viewmodel.DashboardMenuViewModel
import com.app.secureglobal.interfaces.OnItemSelected
import com.app.secureglobal.view.base.BaseFragment
import com.bumptech.glide.Glide

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

        Glide.with(requireActivity())
            .load(R.drawable.applogo)
            .circleCrop()
            .into(binding.ivLogo);

        addMoodData()
        setAction()
        setBottomTab()
        return binding.root
    }

    private fun setBottomTab() {
        binding.txtHome.setTextColor(resources.getColor(R.color.tab_selected_bg))
        binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_un_selected_bg))

        binding.llTab1.visibility  = View.VISIBLE
        binding.llTab2.visibility  = View.GONE
    }

    private fun setAction() {
         binding.cardTipOfTheDay.setOnClickListener {
             val iDashboard = Intent(activity, DetailActivity::class.java)
             iDashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
             iDashboard.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
             startActivity(iDashboard)
         }

        binding.llHome.setOnClickListener {
            binding.llTab1.visibility = View.VISIBLE
            binding.llTab2.visibility = View.GONE


            binding.txtHome.setTextColor(resources.getColor(R.color.tab_selected_bg))
            binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_un_selected_bg))

            binding.ivHome.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.ivAnalytics.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_un_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY);
        }

        binding.llAnalystic.setOnClickListener {
            binding.llTab1.visibility = View.GONE
            binding.llTab2.visibility = View.VISIBLE

            binding.txtHome.setTextColor(resources.getColor(R.color.tab_un_selected_bg))
            binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_selected_bg))

            binding.ivHome.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_un_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.ivAnalytics.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.tab_selected_bg), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    private fun addMoodData() {
        moodDataList.add(MoodDataModel("Sleep","",R.drawable.list_sleep_icon))
        moodDataList.add(MoodDataModel("Gratitude","",R.drawable.list_graditity_icon))
        moodDataList.add(MoodDataModel("Mood","",R.drawable.list_mood_icon))


        moodDataList.add(MoodDataModel("Sleep","",R.drawable.list_sleep_icon))
        moodDataList.add(MoodDataModel("Gratitude","",R.drawable.list_graditity_icon))
        moodDataList.add(MoodDataModel("Mood","",R.drawable.list_mood_icon))

        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvSleep.layoutManager = layoutManager
        binding.rvSleep.adapter = MoodAdapter(requireActivity(), moodDataList,object :
            OnItemSelected<MoodDataModel> {

            override fun onItemSelected(t: MoodDataModel?, position: Int) {
                //  clickMenuEvent(t)
            }

        })

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_nav_menup)
        (context as DashboardActivity).setTitle()
    }
}