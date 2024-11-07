package com.app.dailyjounral.viewmodel

import android.content.Context
import android.os.Bundle
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
import com.app.secureglobal.model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OTPViewModel(private val context: Context, private val binding: OtpPasswordBinding, private val otpFragment: OtpPasswordFragment) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()
    var otp1 : ObservableField<String> = ObservableField()
    var otp2 : ObservableField<String> = ObservableField()
    var otp3 : ObservableField<String> = ObservableField()
    var otp4 : ObservableField<String> = ObservableField()

     private var otp : String = ""

    fun redirectToChangePassword(){
        otpFragment.findNavController().navigate(R.id.ResetPasswordFragment)
    }

    fun onVerifyOTPClicked(){

        val model = SetOTPVerificationData()
        model.emailId = email.get()
        model.otp = this.otp

        otp = otp1.get().toString() + otp2.get().toString() +otp3.get().toString() + otp4.get().toString()

        if (model.otp.toString().length < 4){
            Utils().showSnackBar(context,context.resources.getString(R.string.otp_validation),binding.constraintLayout)
        }
        else{
            callVerificationOTPApi(model)
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
                            val bundle = Bundle()
                            bundle.putString(AppConstants.emailId, email.get().toString())
                            otpFragment.findNavController().navigate(R.id.ResetPasswordFragment,bundle)
                            Utils().showSnackBar(context, t.getMessage().toString(), binding.constraintLayout)
                        }
                    }
                })
        } else {
            Utils().showSnackBar(context, context.getString(R.string.nointernetconnection).toString(), binding.constraintLayout)
        }
    }
}