package com.app.dailyjounral.model.getUpdateProfileResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetUpdateProfileResponse {
    @SerializedName("data")
    @Expose
    private var data: GetUpdateProfileData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getData(): GetUpdateProfileData? {
        return data
    }

    fun setData(data: GetUpdateProfileData?) {
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