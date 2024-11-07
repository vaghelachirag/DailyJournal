package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentMyProfileBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.MyProfileViewModel
import com.bumptech.glide.Glide

class MyProfileFragment: BaseFragment() {

    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!
    private val myProfileViewModel by lazy { MyProfileViewModel(activity as Context,binding,this@MyProfileFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        binding.viewModel = myProfileViewModel
        binding.lifecycleOwner = this
        myProfileViewModel.init()


        binding.btnLogin.setOnClickListener {
         //   forgotPasswordViewModel.redirectToOTP()
        }

        myProfileViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        Glide.with(this)
            .load(R.drawable.user_image)
            .circleCrop()
            .into(binding.ivProfileImage)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
