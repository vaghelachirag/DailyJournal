package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ResetPasswordBinding
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getLoginResponse.SetLoginModel
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.fragment.ResetPasswordFragment
import com.app.dailyjounral.model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResetPasswordViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, private val binding: ResetPasswordBinding, private val resetPasswordFragment: ResetPasswordFragment) : BaseViewModel(){

    // Login Params
     var email : ObservableField<String> = ObservableField()
      var newPassword : ObservableField<String> = ObservableField()
      var confirmPassword : ObservableField<String> = ObservableField()

    fun onResetPasswordClicked(){
        val model = SetLoginModel()
        model.email = email.get()
        model.password = newPassword.get()
        model.confirmPassword = confirmPassword.get()

        if (model.email == null){

            Utils().showSnackBar(context,context.resources.getString(R.string.email_validation),binding.constraintLayout)
        }
        else if (!Utility.isEmailValid(model.email.toString())){
            Utils().showSnackBar(context,context.resources.getString(R.string.email_valid_validation),binding.constraintLayout)
        }
        else if (model.password == null ){
          //  Utils().showSnackBar(context,context.resources.getString(R.string.password_validation),binding.constraintLayout)
            binding.edtNewPassword.requestFocus()
            binding.edtNewPassword.error = context.resources.getString(R.string.password_validation)
        }
        else if (model.password.toString().length < 4 ){
         //   Utils().showSnackBar(context,context.resources.getString(R.string.password_valid_validation),binding.constraintLayout)
            binding.edtNewPassword.requestFocus()
            binding.edtNewPassword.error = context.resources.getString(R.string.password_valid_validation)
        }
        else if (model.password.toString() != model.confirmPassword.toString()){
          //  Utils().showSnackBar(context,context.resources.getString(R.string.confirm_password_validation),binding.constraintLayout)
            binding.edtConfirmPassword.requestFocus()
            binding.edtConfirmPassword.error = context.resources.getString(R.string.confirm_password_validation)
        }
        else{
            callResetPasswordAPI()
        }
    }

    @SuppressLint("HardwareIds")
    private fun callResetPasswordAPI() {

        val params = HashMap<String,Any>()
        params["emailId"] = email.get().toString()
        params["newPassword"] = newPassword.get().toString()
        params["confirmPassword"] = confirmPassword.get().toString()

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getResetPasswordResponse(Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetForgotPasswordResponse>() {
                    override fun onSuccess(response: GetForgotPasswordResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        if (code == 403){
                            Utility.sessionExpired(context)
                        }else{
                            Utils().showSnackBar(context,message,binding.constraintLayout)
                        }
                    }

                    override fun onNext(t: GetForgotPasswordResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            resetPasswordFragment.findNavController().navigate(R.id.LoginFragment)
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

}