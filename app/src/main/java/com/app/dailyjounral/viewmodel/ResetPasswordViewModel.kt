package com.app.dailyjounral.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.databinding.ResetPasswordBinding
import com.app.dailyjounral.view.fragment.ChangePasswordFragment
import com.app.dailyjounral.view.fragment.ResetPasswordFragment
import com.app.secureglobal.model.base.BaseViewModel

class ResetPasswordViewModel(private val context: Context, private val binding: ResetPasswordBinding, private val resetPasswordFragment: ResetPasswordFragment) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()
     var password : ObservableField<String> = ObservableField()
     private var confirmPassword : ObservableField<String> = ObservableField()



}