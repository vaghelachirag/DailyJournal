package com.app.dailyjounral.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentForgotPasswordBinding
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getLoginResponse.SetLoginModel
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.fragment.ForgotPasswordFragment
import com.app.secureglobal.model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForgotPasswordViewModel(private val context: Context, private val binding: FragmentForgotPasswordBinding, private val forgotPasswordFragment: ForgotPasswordFragment) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()

    private var signInMutableLiveData: MutableLiveData<SetLoginModel> = MutableLiveData()

    fun redirectToOTP(){
        forgotPasswordFragment.findNavController().navigate(R.id.OtpPasswordFragment)
    }


    fun onForgotPasswordClick(){
        val model = SetLoginModel()
        model.email = email.get()

        if (model.email == null){
            Utils().showSnackBar(context,context.resources.getString(R.string.email_validation),binding.constraintLayout)
        }
        else if (!Utility.isEmailValid(model.email.toString())){
            Utils().showSnackBar(context,context.resources.getString(R.string.email_valid_validation),binding.constraintLayout)
        }

        else{
            callForgotPasswordApi()
        }
    }

    private fun callForgotPasswordApi() {
        val params = HashMap<String,Any>()
        params["emailId"] = email.get().toString()

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getForgotPasswordResponse(Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetForgotPasswordResponse>() {
                    override fun onSuccess(response: GetForgotPasswordResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(t: GetForgotPasswordResponse) {
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            forgotPasswordFragment.findNavController().navigate(R.id.OtpPasswordFragment)
                        }else{
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",t.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context,context.getString(R.string.nointernetconnection).toString())
        }
    }
}