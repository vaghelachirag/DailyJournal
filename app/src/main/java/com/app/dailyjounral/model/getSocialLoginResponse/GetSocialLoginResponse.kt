package com.app.dailyjounral.model.getSocialLoginResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetSocialLoginResponse {

    @SerializedName("data")
    @Expose
    private var data: GetSocialLoginData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getData(): GetSocialLoginData? {
        return data
    }

    fun setData(data: GetSocialLoginData?) {
        this.data = data
    }

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
}