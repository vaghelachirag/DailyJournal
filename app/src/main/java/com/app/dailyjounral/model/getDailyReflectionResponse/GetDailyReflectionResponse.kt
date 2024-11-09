package com.app.dailyjounral.model.getDailyReflectionResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyReflectionResponse {

    @SerializedName("data")
    @Expose
    private var data: GetDailyReflectionResponseData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetDailyReflectionResponseData? {
        return data
    }

    fun setData(data: GetDailyReflectionResponseData?) {
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