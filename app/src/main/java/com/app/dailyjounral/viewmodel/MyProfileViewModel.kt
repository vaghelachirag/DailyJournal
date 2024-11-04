package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentMyProfileBinding
import com.app.dailyjounral.databinding.FragmentRegisterBinding
import com.app.dailyjounral.view.fragment.MyProfileFragment
import com.app.dailyjounral.view.fragment.RegisterFragment
import com.app.secureglobal.model.base.BaseViewModel


class MyProfileViewModel(
    @SuppressLint("StaticFieldLeak") private val context: Context,
    binding: FragmentMyProfileBinding,
    private val myProfileFragment: MyProfileFragment
) : BaseViewModel(){

}