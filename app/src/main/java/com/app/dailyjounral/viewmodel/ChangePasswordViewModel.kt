package com.app.dailyjounral.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.databinding.FragmentForgotPasswordBinding
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.view.fragment.ChangePasswordFragment
import com.app.dailyjounral.view.fragment.ForgotPasswordFragment
import com.app.dailyjounral.view.fragment.LoginFragment
import com.app.secureglobal.model.base.BaseViewModel

class ChangePasswordViewModel(private val context: Context, private val binding: ChangePasswordBinding, private val changePasswordFragment: ChangePasswordFragment) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()
     var password : ObservableField<String> = ObservableField()
     private var confirmPassword : ObservableField<String> = ObservableField()


    fun redirectToOTP(){
        changePasswordFragment.findNavController().navigate(R.id.OtpPasswordFragment)
    }
}