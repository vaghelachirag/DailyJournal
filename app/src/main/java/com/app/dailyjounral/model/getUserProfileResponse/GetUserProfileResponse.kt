package com.app.dailyjounral.model.getUserProfileResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetUserProfileResponse {
    @SerializedName("data")
    @Expose
    private var data: GetUserProfileData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetUserProfileData? {
        return data
    }

    fun setData(data: GetUserProfileData?) {
        this.data = data
    }

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun getMessage(): Any? {
        return message
    }

    fun setMessage(message: Any?) {
        this.message = message
    }
}