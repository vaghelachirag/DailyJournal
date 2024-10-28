package com.app.dailyjounral.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.HomeFragmentBinding
import com.app.dailyjounral.viewmodel.HomeViewModel
import com.app.secureglobal.view.base.BaseFragment

class HomeFragment : BaseFragment(){


    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val homeViewModel by lazy { HomeViewModel(activity as Context,this@HomeFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}