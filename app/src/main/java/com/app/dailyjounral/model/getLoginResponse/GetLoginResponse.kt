package com.app.dailyjounral.model.getLoginResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetLoginResponse {
    @SerializedName("data")
    @Expose
    private var data: GetLoginData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getData(): GetLoginData? {
        return data
    }

    fun setData(data: GetLoginData?) {
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