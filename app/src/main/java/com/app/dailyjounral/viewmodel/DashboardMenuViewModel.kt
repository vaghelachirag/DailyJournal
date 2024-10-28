package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import com.app.dailyjounral.databinding.DashboardMenuFragmentBinding
import com.app.dailyjounral.view.DashboardMenuFragment
import com.app.secureglobal.model.base.BaseViewModel


class DashboardMenuViewModel(
    val context: Context,
    val binding: DashboardMenuFragmentBinding,
    val dashboardMenuFragment: DashboardMenuFragment
) : BaseViewModel() {


    fun init() {
        getDashboardMenu()
    }

    @SuppressLint("HardwareIds")
    fun getDashboardMenu() {


    }
}