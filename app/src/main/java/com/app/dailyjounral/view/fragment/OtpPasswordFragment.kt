package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.OtpPasswordBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.OTPViewModel

class OtpPasswordFragment: BaseFragment() {

    private var _binding: OtpPasswordBinding? = null
    private val binding get() = _binding!!
    private val otpViewModel by lazy { OTPViewModel(activity as Context,binding,this@OtpPasswordFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = OtpPasswordBinding.inflate(inflater, container, false)
        binding.viewModel = otpViewModel
        binding.lifecycleOwner = this

        binding.btnLogin.setOnClickListener {
            otpViewModel.redirectToChangePassword()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
