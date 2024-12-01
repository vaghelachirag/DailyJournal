package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ChangePasswordBinding
import com.app.dailyjounral.view.fragment.ChangePasswordFragment
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.model.getChangePasswordResponse.SetChangePasswordModel
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getLoginResponse.SetLoginModel
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.dialougs.MessageDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChangePasswordViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, private val binding: ChangePasswordBinding, private val changePasswordFragment: ChangePasswordFragment) : BaseViewModel(){

     // Change Password  Params
      var oldPassword : ObservableField<String> = ObservableField()
      var newPassword : ObservableField<String> = ObservableField()
      var confirmPassword : ObservableField<String> = ObservableField()


    fun onChangePasswordClick(){

        if (oldPassword.get() == null){
            Utils().showSnackBar(context,context.resources.getString(R.string.old_password_validation),binding.constraintLayout)
        }
        else if (newPassword.get() == null ){
            Utils().showSnackBar(context,context.resources.getString(R.string.new_password_validation),binding.constraintLayout)
        }
        else if (confirmPassword.get().toString().length < 4 ){
            Utils().showSnackBar(context,context.resources.getString(R.string.password_valid_validation),binding.constraintLayout)
        }
        else if (confirmPassword.get() == null ){
            Utils().showSnackBar(context,context.resources.getString(R.string.confirm_password_validation),binding.constraintLayout)
        }
        else if (newPassword.get() != confirmPassword.get()){
            Utils().showSnackBar(context,context.resources.getString(R.string.confirm_password_validation),binding.constraintLayout)
        }
        else{
            callChangePasswordApi()
        }
    }

    // Call Api For Change Password
    private fun callChangePasswordApi() {
        val model = SetChangePasswordModel()
        model.oldPassword = oldPassword.get()
        model.newPassword = newPassword.get()
        model.confirmPassword = confirmPassword.get()

        val params = HashMap<String,Any>()
        params["oldPassword"] =   oldPassword.get().toString()
        params["createPassword"] = newPassword.get().toString()
        params["confirmPassword"] = confirmPassword.get().toString()

        val session = Session(context)
        Log.e("Token", session.user!!.token)

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getChangePasswordResponse("Bearer " + session.user!!.token,Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetForgotPasswordResponse>() {
                    override fun onSuccess(response: GetForgotPasswordResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(t: GetForgotPasswordResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                            MessageDialog(context, t.getMessage().toString()).show()
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