package com.app.dailyjounral.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.dailyjounral.databinding.LoginscreenBinding
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.viewmodel.LoginViewModel
import com.app.secureglobal.view.base.BaseFragment



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : BaseFragment() {

    private var _binding: LoginscreenBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel by lazy { LoginViewModel(activity as Context,this@LoginFragment,binding) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = LoginscreenBinding.inflate(inflater, container, false)
        binding.viewModel = signInViewModel
        binding.lifecycleOwner = this

        val email: String = session.getDataByKey(Session.KEY_USER_EMAIL, "")
        val pwd: String = session.getDataByKey(Session.KEY_USER_PASSWORD, "")
        session.storeDataByKey(Session.KEY_USER_NAME,"")
        signInViewModel.email.set(email)
        signInViewModel.password.set(pwd)


        signInViewModel.isLoading.observe(requireActivity()) { isLoading ->
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