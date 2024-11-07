package com.app.dailyjounral.viewmodel

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.OtpPasswordBinding
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getOTPVerificationResponse.SetOTPVerificationData
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.fragment.OtpPasswordFragment
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.model.getRegisterResponse.GetRegisterUserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OTPViewModel(private val context: Context, private val binding: OtpPasswordBinding, private val otpFragment: OtpPasswordFragment) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()
    var otp1 : ObservableField<String> = ObservableField()
    var otp2 : ObservableField<String> = ObservableField()
    var otp3 : ObservableField<String> = ObservableField()
    var otp4 : ObservableField<String> = ObservableField()
    var from : ObservableField<String> = ObservableField()

     private var otp : String = ""

    // Register User
    var fullName : ObservableField<String> = ObservableField()
    var otpCode : ObservableField<String> = ObservableField()
    var registerEmail : ObservableField<String> = ObservableField()
    var registerpassword : ObservableField<String> = ObservableField()
    var registerUserAdult : ObservableField<Boolean> = ObservableField()

    fun redirectToChangePassword(){
        otpFragment.findNavController().navigate(R.id.ResetPasswordFragment)
    }

    fun onVerifyOTPClicked(){

        val model = SetOTPVerificationData()
        model.emailId = email.get()
        model.otp = this.otp

        otp = otp1.get().toString() + otp2.get().toString() +otp3.get().toString() + otp4.get().toString()

        if (otp.length < 4){
            Utils().showSnackBar(context,context.resources.getString(R.string.otp_validation),binding.constraintLayout)
        }
        else{
            if (from.get() == AppConstants.fromRegister){
               if (otp != otpCode.get()){
                   Utils().showSnackBar(context,context.resources.getString(R.string.otp_not_match_validation),binding.constraintLayout)
               }else{
                   callRegisterUserAPI()
               }
            }else{
                callVerificationOTPApi(model)
            }
        }
    }

    // Call  Api  For Register User
    private fun callRegisterUserAPI() {

        val params = HashMap<String,Any>()
        params["userId"] = 0
        params["fullName"] = fullName.get().toString()
        params["emailId"] = registerEmail.get().toString()
        params["password"] = registerpassword.get().toString()
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
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            otpFragment.findNavController().navigate(R.id.LoginFragment)
                        }else{
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


    private fun callVerificationOTPApi(model: SetOTPVerificationData) {

        val params = HashMap<String,Any>()
        params["emailId"] = email.get().toString()
        params["otp"] =  model.otp.toString()

        if (Utility.isNetworkConnected(context)) {
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getVerificationCodeResponse(Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetForgotPasswordResponse>() {
                    override fun onSuccess(response: GetForgotPasswordResponse) {
                        isLoading.postValue(false)
                    }
                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }
                    override fun onNext(t: GetForgotPasswordResponse) {
                        isLoading.postValue(false)
                        if (t.getSuccess() == true) {
                            val bundle = Bundle()
                            bundle.putString(AppConstants.emailId, email.get().toString())
                            otpFragment.findNavController().navigate(R.id.ResetPasswordFragment,bundle)
                            Utils().showSnackBar(context, t.getMessage().toString(), binding.constraintLayout)
                        } else {
                            Utils().showSnackBar(context, t.getMessage().toString(), binding.constraintLayout)
                        }
                    }
                })
        } else {
            Utils().showSnackBar(context, context.getString(R.string.nointernetconnection).toString(), binding.constraintLayout)
        }
    }
}