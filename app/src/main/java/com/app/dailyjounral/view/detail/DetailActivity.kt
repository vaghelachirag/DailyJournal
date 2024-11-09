package com.app.dailyjounral.view.detail
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.databinding.DataBindingUtil
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.DetailActivityBinding
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.base.BaseActivity
import com.app.dailyjounral.viewmodel.DetailViewModel


class DetailActivity : BaseActivity(){


    private lateinit var binding: DetailActivityBinding

    // Session
    private var session: Session? = null


    @SuppressLint("DiscouragedPrivateApi", "SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.lifecycleOwner = this
        session = Session(this)

/*

        Glide.with(this)
            .load(R.drawable.applogo)
            .circleCrop()
            .into(binding.ivLogo)
*/

        setCurrentDate()
        setHeader()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setHeader() {
         if(AppConstants.detailType == 1){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detai_tip_of_day_text))
            setTipOfDayData(false)
         }
        if(AppConstants.detailType == 2){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_quote))
            setTipOfDayData(true)
        }
        if(AppConstants.detailType == 3){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_daily_journal))
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
        }
        if(AppConstants.detailType == 8){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_workout))
            setMoodDetectorData()
        }
        if(AppConstants.detailType == 9){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_quote))
            setTipOfDayData(true)
        }
        if(AppConstants.detailType == 10){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_selfcare_tip))
            setTipOfDayData(true)
        }

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


    private fun setCurrentDate() {
        binding.txtCurrentDate.text = Utils().getCurrentDate()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return false
    }
}