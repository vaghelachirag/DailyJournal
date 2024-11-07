package com.app.dailyjounral.model.getSendOTPResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetSendOTPResponse {

    @SerializedName("data")
    @Expose
    private var data: String? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getData(): String? {
        return data
    }

    fun setData(data: String?) {
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