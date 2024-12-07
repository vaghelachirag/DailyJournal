package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentRegisterBinding
import com.app.dailyjounral.model.getRegisterResponse.GetRegisterUserResponse
import com.app.dailyjounral.model.getRegisterResponse.SetRegisterUserModel
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.fragment.RegisterFragment
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getSendOTPResponse.GetSendOTPResponse
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.dialougs.MessageDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SignupViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, val binding: FragmentRegisterBinding, private val registerFragment: RegisterFragment) : BaseViewModel(){

    // Login Params
    var fullName : ObservableField<String> = ObservableField()
    var emailAddress : ObservableField<String> = ObservableField()
    var password : ObservableField<String> = ObservableField()
     var isAdult : MutableLiveData<Boolean> = MutableLiveData()

    fun redirectToLogin(){
        registerFragment.findNavController().navigate(R.id.LoginFragment)
    }

   fun onSignUpInClicked(){
       isAdult.value = true

       val model = SetRegisterUserModel()
       model.userId = 0
       model.fullName = fullName.get()
       model.emailId = emailAddress.get()
       model.password = password.get()

       if (model.fullName == null){
          // Utils().showSnackBar(context,context.resources.getString(R.string.fullName_validation),binding.constraintLayout)
           binding.edtFullName.requestFocus()
           binding.edtFullName.error = context.resources.getString(R.string.fullName_validation)
       }
       else if  (model.emailId == null){
         //  Utils().showSnackBar(context,context.resources.getString(R.string.email_validation),binding.constraintLayout)
           binding.edtEmail.requestFocus()
           binding.edtEmail.error = context.resources.getString(R.string.email_validation)
       }
       else if (!Utility.isEmailValid(model.emailId.toString())){
       //    Utils().showSnackBar(context,context.resources.getString(R.string.email_valid_validation),binding.constraintLayout)
           binding.edtEmail.requestFocus()
           binding.edtEmail.error = context.resources.getString(R.string.email_valid_validation)
       }

       else if (model.password == null ){
          // Utils().showSnackBar(context,context.resources.getString(R.string.password_validation),binding.constraintLayout)
           binding.edtPassword.requestFocus()
           binding.edtPassword.error = context.resources.getString(R.string.password_validation)
       }
       else if (model.password.toString().length < 4 ){
          // Utils().showSnackBar(context,context.resources.getString(R.string.password_valid_validation),binding.constraintLayout)
           binding.edtPassword.requestFocus()
           binding.edtPassword.error = context.resources.getString(R.string.password_valid_validation)
       }
       else if (binding.radioGroupAdult.checkedRadioButtonId == -1){
           MessageDialog(context,context.resources.getString(R.string.adult_validation)).show()
        //   Utils().showSnackBar(context,context.resources.getString(R.string.adult_validation),binding.constraintLayout)
       }
       else{
           callSendOTPAPI()
        //   callRegisterUserAPI()
       }
   }

    private fun callSendOTPAPI() {

        val params = HashMap<String,Any>()
        params["fullName"] = fullName.get().toString()
        params["emailId"] =  emailAddress.get().toString()


        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSendOTPToEmail(Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetSendOTPResponse>() {
                    override fun onSuccess(response: GetSendOTPResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(getSendOTPResponse: GetSendOTPResponse) {
                        Log.e("Status",getSendOTPResponse.getSuccess().toString())
                        isLoading.postValue(false)
                        if(getSendOTPResponse.getSuccess() == true){
                            Utils().showSnackBar(context,getSendOTPResponse.getMessage().toString(),binding.constraintLayout)
                            val bundle = Bundle()
                            bundle.putString(AppConstants.from, AppConstants.fromRegister)
                            bundle.putString("OTP", getSendOTPResponse.getData())
                            bundle.putString("FirstName", fullName.get().toString())
                            bundle.putString("EmailId", emailAddress.get().toString())
                            bundle.putString("Password", password.get().toString())
                            bundle.putBoolean("IsAdult", isAdult.value!!)
                            registerFragment.findNavController().navigate(R.id.OtpPasswordFragment,bundle)
                        }else{
                            //  Utils().showToast(context,t.getMessage().toString())
                           // Utils().showSnackBar(context,getSendOTPResponse.getMessage().toString(),binding.constraintLayout)
                            MessageDialog(context,getSendOTPResponse.getMessage().toString()).show()
                        }
                        Log.e("StatusCode",getSendOTPResponse.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context,context.getString(R.string.nointernetconnection).toString())
        }
    }


    // Call  Api  For Register User
    private fun callRegisterUserAPI() {

        val params = HashMap<String,Any>()
        params["userId"] = 0
        params["fullName"] = fullName.get().toString()
        params["emailId"] = emailAddress.get().toString()
        params["password"] = password.get().toString()
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

                        if (code == 403){
                            Utility.sessionExpired(context)
                        }else{
                            Utils().showSnackBar(context,message,binding.constraintLayout)
                        }
                    }

                    override fun onNext(t: GetRegisterUserResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                            registerFragment.findNavController().navigate(R.id.LoginFragment)
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

    fun getSocialLoginResponse(displayName: String?, email: String?, socialType: Int) {
        val params = HashMap<String,Any>()
        params["fullname"] = displayName.toString()
        params["email"] = email.toString()
        params["socialtype"] = socialType

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSocialLoginResponse(Utils().getUserToken(context),Networking.wrapParams(params))
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