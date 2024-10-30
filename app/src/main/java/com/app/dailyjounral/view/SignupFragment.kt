package com.app.dailyjounral.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dailyjounral.databinding.SignupFragmentBinding
import com.app.dailyjounral.viewmodel.SignupViewModel
import com.app.dailyjounral.view.base.BaseFragment

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SignupFragment : BaseFragment(){

    private var _binding: SignupFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // ViewModel
    private val signupViewModel by lazy { SignupViewModel(activity as Context,this@SignupFragment,binding) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = SignupFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = signupViewModel
        binding.lifecycleOwner = this


        signupViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}