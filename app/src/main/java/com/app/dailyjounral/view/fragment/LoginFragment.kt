package com.app.dailyjounral.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.LoginViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : BaseFragment() , GoogleApiClient.OnConnectionFailedListener{

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel by lazy { LoginViewModel(activity as Context,binding,this@LoginFragment) }


    // For Google Sign In
    private val RC_SIGN_IN = 9001
    private var mGoogleApiClient: GoogleApiClient? = null

    private var callbackManager = CallbackManager.Factory.create()

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d("bett", "onConnectionFailed:$connectionResult");
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = signInViewModel
        binding.lifecycleOwner = this

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(requireActivity())
            .enableAutoManage(requireActivity() /* FragmentActivity */,this /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

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

        binding.cardGoogle.setOnClickListener {
            signInWithGoogle()
        }
        binding.cardFacebook.setOnClickListener {
           callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                      //  startActivity(Intent(applicationContext, AuthenticatedActivity::class.java))
                    }

                    override fun onCancel() {
                        Log.d("MainActivity", "Facebook onCancel.")

                    }

                    override fun onError(error: FacebookException) {
                        Log.d("MainActivity", "Facebook onError.")

                    }
                })
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


    private fun signInWithGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient!!).setResultCallback {
            Log.e("Signout","Signout")
        }
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient!!)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Deprecated("Deprecated in Java", ReplaceWith("super.onActivityResult(requestCode, resultCode, data)", "com.app.dailyjounral.view.base.BaseFragment"))
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            handleSignInResult(result!!)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            //  updateUI(true)
            Log.e("Result","Success" + result.signInAccount!!.email)
            signInViewModel.getSocialLoginResponse(result.signInAccount!!.displayName,result.signInAccount!!.email,1)
        }else{
            Log.e("Result","Fail" + result.status)
        }
    }

    override fun onPause() {
        super.onPause()
        mGoogleApiClient!!.stopAutoManage(requireActivity())
        mGoogleApiClient!!.disconnect()
    }
}