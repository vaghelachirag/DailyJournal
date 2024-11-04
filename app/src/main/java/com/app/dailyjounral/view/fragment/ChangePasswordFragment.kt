package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.databinding.FragmentForgotPasswordBinding
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.ChangePasswordViewModel
import com.app.dailyjounral.viewmodel.ForgotPasswordViewModel
import com.app.dailyjounral.viewmodel.LoginViewModel

class ChangePasswordFragment: BaseFragment() {

    private var _binding: ChangePasswordBinding? = null
    private val binding get() = _binding!!
    private val changePasswordViewModel by lazy { ChangePasswordViewModel(activity as Context,binding,this@ChangePasswordFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ChangePasswordBinding.inflate(inflater, container, false)
        binding.viewModel = changePasswordViewModel
        binding.lifecycleOwner = this


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
