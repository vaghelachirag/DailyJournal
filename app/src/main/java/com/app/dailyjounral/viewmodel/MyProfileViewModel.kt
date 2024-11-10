package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentMyProfileBinding
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.model.getUserProfileResponse.GetUserProfileResponse
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.dialougs.MessageDialog
import com.app.dailyjounral.view.fragment.MyProfileFragment
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class MyProfileViewModel(@SuppressLint("StaticFieldLeak") private val context: Context, val binding: FragmentMyProfileBinding, private val myProfileFragment: MyProfileFragment) : BaseViewModel(){
    // Change Password  Params
    var firstName : ObservableField<String> = ObservableField()
    var emailAddress : ObservableField<String> = ObservableField()
    var userProfileUrl : ObservableField<String> = ObservableField()

    var imageFile : MutableLiveData<File> = MutableLiveData<File>()

    val session  = Session(context)
    fun init() {

        if (!session.isLoggedIn){
            myProfileFragment.findNavController().navigate(R.id.LoginFragment)
            return
        }
        getUserProfileApi()
    }


    //    For Save Survey Picture
    fun saveUpdateUserProfile() {
        var requestBody: RequestBody? = null
        var isAdult = false
        if (binding.rbYes.isChecked){
            isAdult = true
        }

        if (imageFile.value != null){
            requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("FullName", binding.edtFullName.text.toString())
                .addFormDataPart("Profile", imageFile.value!!.name, imageFile.value!!.asRequestBody("image/*".toMediaTypeOrNull()))
                .addFormDataPart("IsAdult",isAdult.toString())
                .build()
        }else{
            requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("FullName", binding.edtFullName.text.toString())
                .addFormDataPart("IsAdult", isAdult.toString())
                .build()
        }


        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .saveUpdateUserProfile(Utils().getUserToken(context),requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetUserProfileResponse>() {
                    override fun onSuccess(response: GetUserProfileResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                    }

                    override fun onNext(getUpdateProfileResponse: GetUserProfileResponse) {
                        Log.e("Status",getUpdateProfileResponse.getSuccess().toString())
                        isLoading.postValue(false)
                        if(getUpdateProfileResponse.getSuccess() == true){
                            MessageDialog(context, getUpdateProfileResponse.getMessage().toString()).show()
                          //  Utils().showSnackBar(context,getUpdateProfileResponse.getMessage().toString(),binding.constraintLayout)
                        }else{
                            //  Utils().showToast(context,t.getMessage().toString())
                            Utils().showSnackBar(context,getUpdateProfileResponse.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",getUpdateProfileResponse.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context,context.getString(R.string.nointernetconnection).toString())
        }
    }

    private fun getUserProfileApi() {
        val session = Session(context)
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getUserProfileResponse(Utility.getUserToken(context), session.user!!.userId.toString())
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
            Glide.with(context)
                .load(userProfileResponse.getData()!!.getProfilePicture())
                .circleCrop()
                .error(R.drawable.icon_placeholder)
                .placeholder(R.drawable.icon_placeholder)
                .into(binding.ivProfileImage)
          //  Glide.with(context).load(userProfileResponse.getData()!!.getProfilePicture()).apply(Utility.getGlideRequestOption()).into(binding.ivProfileImage)
        }
        if (userProfileResponse.getData()!!.getIsAdult() == true){
            binding.rbYes.isChecked = true
        }else{
            binding.rbNo.isChecked = true
        }
    }
}