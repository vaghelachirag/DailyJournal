package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.FragmentRegisterBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.SignupViewModel

class RegisterFragment: BaseFragment()  {


    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val signupViewModel by lazy { SignupViewModel(activity as Context,binding,this@RegisterFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = signupViewModel
        binding.lifecycleOwner = this

        binding.txtRedirectToLogin.setOnClickListener {
            signupViewModel.redirectToLogin()
        }

        signupViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        return binding.root

    }

}