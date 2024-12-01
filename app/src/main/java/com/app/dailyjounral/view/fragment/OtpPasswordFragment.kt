package com.app.dailyjounral.view.fragment

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

        if (requireArguments().getString(AppConstants.from) == AppConstants.fromRegister){
            otpViewModel.from.set(AppConstants.fromRegister)
            setRegisterData()
        }else{
            otpViewModel.from.set(AppConstants.fromOTP)
        }

        val emailId = requireArguments().getString(AppConstants.emailId);
        otpViewModel.email.set(emailId)
        Log.e("EmailId",emailId.toString())
        binding.viewModel = otpViewModel
        binding.lifecycleOwner = this

        binding.edtOTP1.text
        binding.btnLogin.setOnClickListener {
            otpViewModel.redirectToChangePassword()
        }

        otpViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        binding.txtResend.setOnClickListener {
            otpViewModel.callSendOTPAPI()
            startTimer()
        }
        setOtpToNext()

        startTimer()

        return binding.root

    }

    private fun startTimer() {
        val cTimer = object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.txtDidntReceive.visibility = View.GONE
                binding.txtResend.text = "Resend OTP in : " + (millisUntilFinished / 1000).toString() + " Sec"
            }

            override fun onFinish() {
                binding.txtDidntReceive.visibility = View.VISIBLE
                binding.txtResend.text = "Resend OTP"
                binding.txtResend.setEnabled(true)
            }
        }
        cTimer.start()
    }

    private fun setRegisterData() {
        otpViewModel.otpCode.set(requireArguments().getString("OTP"))
        otpViewModel.fullName.set(requireArguments().getString("FirstName"))
        otpViewModel.registerEmail.set(requireArguments().getString("EmailId"))
        otpViewModel.registerpassword.set(requireArguments().getString("Password"))
        otpViewModel.registerUserAdult.set(requireArguments().getBoolean("IsAdult"))
    }

    private fun setOtpToNext() {
        binding.edtOTP1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.edtOTP2.requestFocus();
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.edtOTP2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.edtOTP3.requestFocus();
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.edtOTP3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.edtOTP4.requestFocus();
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

}
