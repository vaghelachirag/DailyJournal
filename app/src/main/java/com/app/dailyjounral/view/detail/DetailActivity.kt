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
import com.bumptech.glide.Glide


class DetailActivity : BaseActivity(){


    private lateinit var binding: DetailActivityBinding

    // Session
    private var session: Session? = null

    private val detailViewModel by lazy { DetailViewModel(this,binding) }

    @SuppressLint("DiscouragedPrivateApi", "SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this
        session = Session(this)


        Glide.with(this)
            .load(R.drawable.applogo)
            .circleCrop()
            .into(binding.ivLogo)


        setCurrentDate()
        detailViewModel.init()
        setDetailType()
        setHeader()
    }

    private fun setHeader() {
         if(AppConstants.detailType == 1){
            binding.ivTextLogo.setImageDrawable(resources.getDrawable(R.drawable.detail_sleep))
         }
    }

    private fun setDetailType() {
        if (AppConstants.detailType == 1){
            binding.ivImage.visibility = View.GONE
            binding.llDailyJournal.visibility = View.VISIBLE
            binding.llMood.visibility = View.GONE
            binding.llGratuity.visibility = View.GONE
        }

        if (AppConstants.detailType == 2){
            binding.ivImage.visibility = View.VISIBLE
            binding.llDailyJournal.visibility = View.VISIBLE
            binding.llMood.visibility = View.GONE
            binding.llGratuity.visibility = View.GONE
        }

        if (AppConstants.detailType == 3){
            binding.ivImage.visibility = View.GONE
            binding.llDailyJournal.visibility = View.GONE
            binding.llMood.visibility = View.VISIBLE
            binding.llGratuity.visibility = View.GONE
        }

        if (AppConstants.detailType == 0){
            binding.ivImage.visibility = View.GONE
            binding.llDailyJournal.visibility = View.GONE
            binding.llMood.visibility = View.GONE
            binding.llGratuity.visibility = View.VISIBLE
        }
    }

    private fun setCurrentDate() {
        binding.txtCurrentDate.text = Utils().getCurrentDate()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return false
    }
}