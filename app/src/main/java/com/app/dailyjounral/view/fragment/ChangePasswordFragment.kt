package com.app.dailyjounral.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.ChangePasswordViewModel
class ChangePasswordFragment: BaseFragment() {

    private var _binding: ChangePasswordBinding? = null
    private val binding get() = _binding!!
    private val changePasswordViewModel by lazy { ChangePasswordViewModel(activity as Context,binding,this@ChangePasswordFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ChangePasswordBinding.inflate(inflater, container, false)
        binding.viewModel = changePasswordViewModel
        binding.lifecycleOwner = this

        changePasswordViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
