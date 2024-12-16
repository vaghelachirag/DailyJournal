package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.databinding.FragmentAnalyticsBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.view.base.menu.DashboardActivity
import com.app.dailyjounral.viewmodel.AnalyticsViewModel
import com.app.dailyjounral.viewmodel.ChangePasswordViewModel
class AnalyticsFragment: BaseFragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!
    private val analyticsViewModel by lazy { AnalyticsViewModel(activity as Context,binding,this@AnalyticsFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        binding.viewModel = analyticsViewModel
        binding.lifecycleOwner = this

        setAction()
        return binding.root

    }

    private fun setAction() {
        binding.cardGeneralEntries.setOnClickListener {
            (activity as DashboardActivity).navController.navigate(R.id.GeneralEntryFragment)
        }
        binding.cardMyTrends.setOnClickListener {
            Log.e("MyTrends","My Trends")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
