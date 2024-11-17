package com.app.dailyjounral.view.fragment

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentSender.SendIntentException
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
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import java.util.Arrays


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel by lazy { LoginViewModel(activity as Context,binding,this@LoginFragment) }


    private val RC_SIGN_IN = 1

    private var mGoogleSignInClient: GoogleSignInClient? = null

    private var webServerKey : String = "AIzaSyBoD5scdG-kPGWhOGERu96N0BWvPceipjQ"

    private  val REQUEST_CODE_GOOGLE_SIGN_IN: Int = 1 /* unique request id */

    private var callbackManager: CallbackManager? = null

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

        binding.cardGoogle.setOnClickListener {
            signIn()
        }
        binding.cardFacebook.setOnClickListener {
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
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


    private fun signIn() {
        val request =
            GetSignInIntentRequest.builder()
                .setServerClientId(webServerKey)
                .build()

        Identity.getSignInClient(requireActivity())
            .getSignInIntent(request)
            .addOnSuccessListener { result ->
                try {
                    startIntentSenderForResult(
                        result.getIntentSender(),
                        REQUEST_CODE_GOOGLE_SIGN_IN,  /* fillInIntent= */
                        null,  /* flagsMask= */
                        0,  /* flagsValue= */
                        0,  /* extraFlags= */
                        0,  /* options= */
                        null
                    )
                } catch (e: SendIntentException) {
                    Log.e("TAG", "Google Sign-in failed")
                }
            }
            .addOnFailureListener { e ->
                Log.e("TAG", "Google Sign-in failed", e)
            }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account : GoogleSignInAccount? = task.getResult(ApiException::class.java)
                val personName = account!!.displayName
                Log.e("PersionName",personName.toString())
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.e("TAG","signInResult:failed code=" + e.statusCode)
            }
        }
        callbackManager?.onActivityResult(requestCode, resultCode, data)

    }

}