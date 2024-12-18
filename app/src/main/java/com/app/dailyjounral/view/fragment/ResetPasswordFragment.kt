package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.databinding.ResetPasswordBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.ChangePasswordViewModel
import com.app.dailyjounral.viewmodel.ResetPasswordViewModel

class ResetPasswordFragment: BaseFragment() {

    private var _binding: ResetPasswordBinding? = null
    private val binding get() = _binding!!
    private val resetPasswordViewModel by lazy { ResetPasswordViewModel(activity as Context,binding,this@ResetPasswordFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ResetPasswordBinding.inflate(inflater, container, false)
        binding.viewModel = resetPasswordViewModel
        binding.lifecycleOwner = this


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
