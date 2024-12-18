package com.app.dailyjounral.view.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.view.base.menu.DashboardActivity
import com.app.dailyjounral.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.button.MaterialButton


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

 //   private var callbackManager = CallbackManager.Factory.create()

    private var gso : GoogleSignInOptions? = null
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
            showAreYouAdultDialog(requireActivity())

        }


        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                @SuppressLint("RestrictedApi")
                override fun handleOnBackPressed() {
                    Log.e("Back","Back")
                    (context as DashboardActivity).navController.navigate(R.id.dashboardMenuFragment)
                }
            })

        binding.cardFacebook.setOnClickListener {
         /*  callbackManager = CallbackManager.Factory.create()
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
                })*/
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
            signInViewModel.getSocialLoginResponse(result.signInAccount!!.displayName,result.signInAccount!!.email,1)
            Log.e("Result","Success" + result.signInAccount!!.email)

        }else{
            Log.e("Result","Fail" + result.status)
        }
    }
    // Show Are you Adult
    private fun showAreYouAdultDialog(context: Context) {

        session = Session(context);

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.attributes.windowAnimations = R.style.DialogTheme;
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_alert_are_you_adult)

        dialog.window!!.setBackgroundDrawableResource(R.color.white);

        // Button
        val buttonOk : MaterialButton = dialog.findViewById(R.id.btnOk)
        val buttonCancel : MaterialButton = dialog.findViewById(R.id.btnCancel)
        val iv_Close : ImageView = dialog.findViewById(R.id.iv_Close)



        iv_Close.setOnClickListener {
            dialog.dismiss()
        }

        val tvValidation : TextView = dialog.findViewById(R.id.tvValidation)
        val tvMessage: TextView = dialog.findViewById(R.id.tvMessage)


        buttonOk.setOnClickListener {
            dialog.dismiss()
            if(mGoogleApiClient!!.isConnected){
                signInWithGoogle()
            }else{

                mGoogleApiClient = gso?.let { it1 ->
                    GoogleApiClient.Builder(requireActivity())
                        .enableAutoManage(requireActivity() /* FragmentActivity */,this /* OnConnectionFailedListener */)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, it1)
                        .build()
                }
            }
        }
        buttonCancel.setOnClickListener {
            iv_Close.visibility = View.VISIBLE
            tvValidation.visibility = View.VISIBLE
            tvValidation.text = context.resources.getString(R.string.adult_msg_validation)
            tvMessage.visibility = View.GONE
            buttonCancel.visibility = View.GONE
            buttonOk.visibility = View.GONE
            //  dialog.dismiss()
        }
        dialog.show()
    }

    override fun onPause() {
        super.onPause()
        mGoogleApiClient!!.stopAutoManage(requireActivity())
        mGoogleApiClient!!.disconnect()
    }
}