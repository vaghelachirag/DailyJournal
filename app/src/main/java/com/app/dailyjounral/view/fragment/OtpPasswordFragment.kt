package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.OtpPasswordBinding
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.OTPViewModel

class OtpPasswordFragment: BaseFragment() {

    private var _binding: OtpPasswordBinding? = null
    private val binding get() = _binding!!
    private val otpViewModel by lazy { OTPViewModel(activity as Context,binding,this@OtpPasswordFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = OtpPasswordBinding.inflate(inflater, container, false)
        val emailId = requireArguments().getString(AppConstants.emailId);
        otpViewModel.email.set(emailId)
        Log.e("EmailId",emailId.toString())
        binding.viewModel = otpViewModel
        binding.lifecycleOwner = this

        binding.btnLogin.setOnClickListener {
            otpViewModel.redirectToChangePassword()
        }

        otpViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
