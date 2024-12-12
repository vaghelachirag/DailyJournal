package com.app.dailyjounral

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.app.dailyjounral.databinding.TestActivityBinding
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getRegisterResponse.GetRegisterUserResponse
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.dialougs.MessageDialog
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TestActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener{

    private lateinit var binding: TestActivityBinding
    private val RC_SIGN_IN = 9001
    private var mGoogleApiClient: GoogleApiClient? = null

     var context: Context =  this
     var isLoading = MutableLiveData<Boolean>()


    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d("bett", "onConnectionFailed:$connectionResult");
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Set the dimensions of the sign-in button.
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this /* FragmentActivity */, this@TestActivity /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

          binding.signInButton.setOnClickListener {
            /*  val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient!!)
              startActivityForResult(signInIntent, RC_SIGN_IN)*/
            //  logout()
              val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient!!)
              startActivityForResult(signInIntent, RC_SIGN_IN)
          }
    }

    private fun logout() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient!!).setResultCallback { }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            handleSignInResult(result!!)
        }

    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
          //  updateUI(true)
            Log.e("Result","Success" + result.signInAccount!!.email)
             callRegisterUserAPI(result.signInAccount!!.displayName,result.signInAccount!!.email,"123456")
        }else{
            Log.e("Result","Fail")
        }
    }

    // Call  Api  For Register User
    private fun callRegisterUserAPI(displayName: String?, email: String?, password: String) {

        val params = HashMap<String,Any>()
        params["userId"] = 0
        params["fullName"] = displayName.toString()
        params["emailId"] = email.toString()
        params["password"] = password
        params["isAdult"] = true

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getRegisterUserResponse(Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetRegisterUserResponse>() {
                    override fun onSuccess(response: GetRegisterUserResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(t: GetRegisterUserResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                          //  Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            callLoginAPI(email,password)
                        }else{
                            callLoginAPI(email,password)
                            //  Utils().showToast(context,t.getMessage().toString())
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",t.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context,context.getString(R.string.nointernetconnection).toString())
        }
    }

    @SuppressLint("HardwareIds")
    private fun callLoginAPI(email: String?, password: String) {
        Session(context)

        val params = HashMap<String,Any>()
        params["email"] = email.toString()
        params["password"] = password

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getLoginResponse(Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetLoginResponse>() {
                    override fun onSuccess(response: GetLoginResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()


                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }
                    override fun onNext(t: GetLoginResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                            MessageDialog(context, t.getMessage().toString()).show()
                            // Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            val session = Session(context)
                            session.isLoggedIn = true
                            session.user = t.getData()
                            Utils().reloadActivity(context)

                        }else{
                            //  Utils().showToast(context,t.getMessage().toString())
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",t.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

}