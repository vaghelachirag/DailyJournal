package com.app.dailyjounral.view.detail
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.databinding.DataBindingUtil
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.DetailActivityBinding
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.base.BaseActivity
import com.app.dailyjounral.viewmodel.DetailViewModel
import java.util.Calendar


@Suppress("DEPRECATION")
class DetailActivity : BaseActivity(){


    private lateinit var binding: DetailActivityBinding

    // Session
    private var session: Session? = null;

    private val detailViewModel by lazy { DetailViewModel(this,binding) }

    @SuppressLint("DiscouragedPrivateApi", "SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this
        session = Session(this);

        setCurrentDate()
        detailViewModel.init()

    }

    private fun setCurrentDate() {
        binding.txtCurrentDate.text = Utils().getCurrentDate()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return false
    }
}