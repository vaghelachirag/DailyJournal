package com.app.dailyjounral.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.DashboardMenuFragmentBinding
import com.app.dailyjounral.databinding.DetailActivityBinding
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.DetailViewModel

class DetailFragment: BaseFragment() {

    private var _binding: DetailActivityBinding? = null

    private val binding get() = _binding!!

    private val detailViewModel by lazy { DetailViewModel(requireActivity(),binding) }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DetailActivityBinding.inflate(inflater, container, false)
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this
        setCurrentDate()
        setHeader()
        detailViewModel.init()

        // Show Progress bar
        detailViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }


        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setHeader() {
        if(AppConstants.detailType == 1){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detai_tip_of_day_text))
            setTipOfDayData(true)
        }
        if(AppConstants.detailType == 2){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_quote))
            setTipOfDayData(true)
        }
        if(AppConstants.detailType == 3){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_daily_journal))
            setTipOfDayData(false)
            setDailyGeneralData()
        }
        if(AppConstants.detailType == 4){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_sleep))
        }
        if(AppConstants.detailType == 5){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_icon_gratitude))
            setGratitudeData()
        }
        if(AppConstants.detailType == 6){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_mood))
            setMoodDetectorData()
        }

        if(AppConstants.detailType == 7){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_goal_setting))
            setTipOfDayData(false)
            setDailyGeneralData()
        }
        if(AppConstants.detailType == 8){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_workout))
            setMoodDetectorData()
        }
        if(AppConstants.detailType == 9){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_selfcare_tip))
            setTipOfDayData(true)
        }
        if(AppConstants.detailType == 10){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_selfcare_tip))
            setTipOfDayData(true)
        }

    }

    private fun setDailyGeneralData() {
         binding.llDailyJournalAns.visibility = View.VISIBLE
    }

    private fun setCurrentDate() {
        binding.txtCurrentDate.text = Utils().getCurrentDate()
    }

    private fun setTipOfDayData(showImage: Boolean) {
        if (showImage){
            binding.ivImage.visibility = View.VISIBLE
        }
        else{
            binding.ivImage.visibility = View.GONE
        }
        binding.llDailyJournal.visibility = View.VISIBLE
        binding.llMood.visibility = View.GONE
        binding.llGratuity.visibility = View.GONE
    }
    private fun setMoodDetectorData() {
        binding.ivImage.visibility = View.GONE
        binding.llDailyJournal.visibility = View.GONE
        binding.llMood.visibility = View.VISIBLE
        binding.llGratuity.visibility = View.GONE
    }

    private fun setGratitudeData() {
        binding.ivImage.visibility = View.GONE
        binding.llDailyJournal.visibility = View.GONE
        binding.llMood.visibility = View.VISIBLE
        binding.llGratuity.visibility = View.VISIBLE
    }
}