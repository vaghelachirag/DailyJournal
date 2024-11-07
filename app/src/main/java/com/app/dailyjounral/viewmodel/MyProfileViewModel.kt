package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentMyProfileBinding
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.model.getUserProfileResponse.GetUserProfileResponse
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.fragment.MyProfileFragment
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MyProfileViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, val binding: FragmentMyProfileBinding, private val myProfileFragment: MyProfileFragment) : BaseViewModel(){
    // Change Password  Params
    var firstName : ObservableField<String> = ObservableField()
    var emailAddress : ObservableField<String> = ObservableField()
    var userProfileUrl : ObservableField<String> = ObservableField()

    fun init() {
        getUserProfileApi()
    }

    private fun getUserProfileApi() {
        val session = Session(context)
        Log.e("Token", session.user!!.token)
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getUserProfileResponse("Bearer " + session.user!!.token, session.user!!.userId.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetUserProfileResponse>() {
                    override fun onSuccess(response: GetUserProfileResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(getUserProfileResponse: GetUserProfileResponse) {
                        Log.e("Status",getUserProfileResponse.getSuccess().toString())
                        isLoading.postValue(false)
                        if(getUserProfileResponse.getSuccess() == true){
                          setUserProfile(getUserProfileResponse)
                        }else{
                            //  Utils().showToast(context,t.getMessage().toString())
                            Utils().showSnackBar(context,getUserProfileResponse.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",getUserProfileResponse.getSuccess().toString())
                    }
                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }

    }

    private fun setUserProfile(userProfileResponse: GetUserProfileResponse) {
        binding.edtFullName.setText(Utility.getNullToBlankString(userProfileResponse.getData()?.getFullName().toString()))
        binding.edtEmail.setText(Utility.getNullToBlankString(userProfileResponse.getData()?.getEmailId().toString()))
        if (userProfileResponse.getData()?.getProfilePicture() != null){
            Glide.with(context).load(userProfileResponse.getData()!!.getProfilePicture()).apply(Utility.getGlideRequestOption()).into(binding.ivProfileImage)
        }
    }
}