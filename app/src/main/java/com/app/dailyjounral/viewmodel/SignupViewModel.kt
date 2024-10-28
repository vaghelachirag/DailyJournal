package com.app.dailyjounral.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.SignupFragmentBinding
import com.app.dailyjounral.view.SignupFragment
import com.app.secureglobal.model.base.BaseViewModel


class SignupViewModel(
    private val context: Context, val signupFragment: SignupFragment, val binding: SignupFragmentBinding) : BaseViewModel(){

    var employeeCode : ObservableField<String> = ObservableField()
    var remark : ObservableField<String> = ObservableField()


    fun redirectToLogin(){
        signupFragment.findNavController().navigate(R.id.action_SignUpFragment_to_LoginFragment)
    }

}