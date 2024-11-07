package com.app.dailyjounral.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.view.base.menu.DashboardActivity
import com.app.dailyjounral.viewmodel.LoginViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel by lazy { LoginViewModel(activity as Context,binding,this@LoginFragment) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = signInViewModel
        binding.lifecycleOwner = this


        binding.txtRedirectToSignup.setOnClickListener {
            signInViewModel.redirectToSignup()
        }

        binding.txtForgotPassword.setOnClickListener {
            signInViewModel.redirectToForgotPassword()
        }

        signInViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        setLoginAndPassword()
        binding.chkRememberPassword.isChecked = session.getDataByKey(Session.KEY_USER_REMEMBER, false)
        return binding.root

    }
    private fun setLoginAndPassword() {
        val email: String = session.getDataByKey(Session.KEY_USER_EMAIL, "")
        val pwd: String = session.getDataByKey(Session.KEY_USER_PASSWORD, "")
        session.storeDataByKey(Session.KEY_USER_NAME,"")
        signInViewModel.email.set(email)
        signInViewModel.password.set(pwd)
    }

}