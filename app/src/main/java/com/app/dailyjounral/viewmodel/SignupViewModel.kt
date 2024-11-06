package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
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
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.fragment.RegisterFragment
import com.app.secureglobal.model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SignupViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, val binding: FragmentRegisterBinding, private val registerFragment: RegisterFragment) : BaseViewModel(){

    // Login Params
    var fullName : ObservableField<String> = ObservableField()
    var emailAddress : ObservableField<String> = ObservableField()
    var password : ObservableField<String> = ObservableField()
    private var isAdult : MutableLiveData<Boolean> = MutableLiveData()

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
       model.isAdult = true

       if (model.fullName == null){
           Utils().showSnackBar(context,context.resources.getString(R.string.fullName_validation),binding.constraintLayout)
       }
       else if  (model.emailId == null){
           Utils().showSnackBar(context,context.resources.getString(R.string.email_validation),binding.constraintLayout)
       }
       else if (!Utility.isEmailValid(model.emailId.toString())){
           Utils().showSnackBar(context,context.resources.getString(R.string.email_valid_validation),binding.constraintLayout)
       }

       else if (model.password == null ){
           Utils().showSnackBar(context,context.resources.getString(R.string.password_validation),binding.constraintLayout)
       }
       else if (model.password.toString().length < 4 ){
           Utils().showSnackBar(context,context.resources.getString(R.string.password_valid_validation),binding.constraintLayout)
       }
       else{
           Session(context)
           callRegisterUserAPI()
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
                    }

                    override fun onNext(t: GetRegisterUserResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                            var session = Session(context)
                            session.isLoggedIn = true
                            /*  session.user = t.getData()
                              t.getData()!!.getProfilePicture()?.let { session.storeUserProfileImageKey(it) }
                              t.getData()!!.getName()?.let { session.storeUserNameKey(it) }
                              redirectToHome()*/
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