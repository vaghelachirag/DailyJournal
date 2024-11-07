package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentLoginBinding
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getLoginResponse.SetLoginModel
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.fragment.LoginFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, private val binding: FragmentLoginBinding, private val loginFragment: LoginFragment) : BaseViewModel(){

    // Login Params
    var email : ObservableField<String> = ObservableField()
    var password : ObservableField<String> = ObservableField()
    private var confirmPassword : ObservableField<String> = ObservableField()

    fun redirectToSignup(){
        loginFragment.findNavController().navigate(R.id.RegisterFragment)
    }

    fun redirectToForgotPassword(){
        loginFragment.findNavController().navigate(R.id.ForgotPasswordFragment)
    }


    fun onSignInClicked(){
        val model = SetLoginModel()
        model.email = email.get()
        model.password = password.get()
        model.confirmPassword = confirmPassword.get()

        if (model.email == null){
            Utils().showSnackBar(context,context.resources.getString(R.string.email_validation),binding.constraintLayout)
        }
        else if (!Utility.isEmailValid(model.email.toString())){
            Utils().showSnackBar(context,context.resources.getString(R.string.email_valid_validation),binding.constraintLayout)
        }
        else if (model.password == null ){
            Utils().showSnackBar(context,context.resources.getString(R.string.password_validation),binding.constraintLayout)
        }
        else if (model.password.toString().length < 4 ){
            Utils().showSnackBar(context,context.resources.getString(R.string.password_valid_validation),binding.constraintLayout)
        }
        else{
            val session = Session(context)

            if (binding.chkRememberPassword.isChecked) {
                session.storeDataByKey(Session.KEY_USER_EMAIL, binding.edtEmail.text.toString())
                session.storeDataByKey(
                    Session.KEY_USER_PASSWORD,
                    binding.edtPassword.text.toString(),
                )
                session.storeDataByKey(
                    Session.KEY_USER_REMEMBER,
                    binding.chkRememberPassword.isChecked,
                )

            } else {
                session.storeDataByKey(Session.KEY_USER_EMAIL, "")
                session.storeDataByKey(Session.KEY_USER_PASSWORD, "")
                session.storeDataByKey(Session.KEY_USER_REMEMBER, false)
                session.storeDataByKey(Session.KEY_USER_NAME,"")
            }
            callLoginAPI()
        }
    }

    @SuppressLint("HardwareIds")
    private fun callLoginAPI() {
        Session(context)

        val params = HashMap<String,Any>()
        params["email"] = email.get().toString()
        params["password"] = password.get().toString()

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
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            val session = Session(context)
                            session.isLoggedIn = true
                            session.user = t.getData()
                            loginFragment.findNavController().navigate(R.id.dashboardMenuFragment)
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