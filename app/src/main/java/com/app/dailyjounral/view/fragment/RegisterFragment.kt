package com.app.dailyjounral.view.fragment

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
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentRegisterBinding
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.viewmodel.SignupViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.button.MaterialButton

class RegisterFragment: BaseFragment(), GoogleApiClient.OnConnectionFailedListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val signupViewModel by lazy { SignupViewModel(activity as Context,binding,this@RegisterFragment) }


    // For Google Sign In
    private val RC_SIGN_IN = 9001
    private var mGoogleApiClient: GoogleApiClient? = null

  //  private var callbackManager = CallbackManager.Factory.create()
    private var gso : GoogleSignInOptions? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = signupViewModel
        binding.lifecycleOwner = this

         gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(requireActivity())
            .enableAutoManage(requireActivity() /* FragmentActivity */,this /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso!!)
            .build()


        binding.txtRedirectToLogin.setOnClickListener {
            signupViewModel.redirectToLogin()
        }

        signupViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        binding.rbYes.setOnCheckedChangeListener { _, _ ->
            signupViewModel.isAdult.value = true
        }

        binding.rbNo.setOnCheckedChangeListener { _, _ ->
            signupViewModel.isAdult.value = false
        }

        binding.cardGoogle.setOnClickListener {
            showAreYouAdultDialog(requireActivity())
        }


        return binding.root

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
            signupViewModel.getSocialLoginResponse(result.signInAccount!!.displayName,result.signInAccount!!.email,1)
        }else{
            Log.e("Result","Fail" + result.status)
        }
    }

    private fun signInWithGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient!!).setResultCallback {
            Log.e("Signout","Signout")
        }
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient!!)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.d("bett", "onConnectionFailed:$p0");
    }

    override fun onPause() {
        super.onPause()
        mGoogleApiClient!!.stopAutoManage(requireActivity())
        mGoogleApiClient!!.disconnect()
    }


    // Show Are you Adult
    private fun showAreYouAdultDialog(context: Context) {

        session = Session(context);

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.attributes.windowAnimations = R.style.DialogTheme;
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_alert_are_you_adult)

        dialog.window!!.setBackgroundDrawableResource(R.color.tip_of_day_gradient_top);

        // Button
        val buttonOk : MaterialButton = dialog.findViewById(R.id.btnOk)
        val buttonCancel : MaterialButton = dialog.findViewById(R.id.btnCancel)

        val tvValidation : TextView = dialog.findViewById(R.id.tvValidation)

        val iv_Close : ImageView = dialog.findViewById(R.id.iv_Close)
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

        iv_Close.setOnClickListener {
            dialog.dismiss()
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
}