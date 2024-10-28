package com.app.dailyjounral.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import com.app.dailyjounral.databinding.LoginscreenBinding
import com.app.dailyjounral.view.LoginFragment
import com.app.secureglobal.model.base.BaseViewModel

class LoginViewModel(private val context: Context, private val loginFragment: LoginFragment, private val binding: LoginscreenBinding) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()
     var password : ObservableField<String> = ObservableField()
     private var confirmPassword : ObservableField<String> = ObservableField()


}