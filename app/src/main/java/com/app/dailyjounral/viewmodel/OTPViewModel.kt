package com.app.dailyjounral.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.databinding.FragmentForgotPasswordBinding
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.databinding.OtpPasswordBinding
import com.app.dailyjounral.view.fragment.ChangePasswordFragment
import com.app.dailyjounral.view.fragment.ForgotPasswordFragment
import com.app.dailyjounral.view.fragment.OtpPasswordFragment
import com.app.secureglobal.model.base.BaseViewModel

class OTPViewModel(private val context: Context, private val binding: OtpPasswordBinding, private val otpFragment: OtpPasswordFragment) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()
     var password : ObservableField<String> = ObservableField()
     private var confirmPassword : ObservableField<String> = ObservableField()

    fun redirectToChangePassword(){
        otpFragment.findNavController().navigate(R.id.ResetPasswordFragment)
    }

}