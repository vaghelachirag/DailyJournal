package com.app.dailyjounral.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.viewmodel.LoginViewModel
import com.app.dailyjounral.view.base.BaseFragment



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel by lazy { LoginViewModel(activity as Context,binding) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
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

        setLoginSpanText()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setLoginSpanText() {
        val loginText : Spannable = SpannableString(resources.getString(R.string.signup_text))
        //loginText.setSpan( StyleSpan(Typeface.BOLD), 20, 32, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
      //  str.setSpan(new ForegroundColorSpan(Color.BLUE, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginText.setSpan(
            ForegroundColorSpan(Color.RED),
            /* start index */ 8, /* end index */ 12,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.txtRedirectToSignup.text = loginText
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}