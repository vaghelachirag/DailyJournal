package com.app.dailyjounral.model.getMoodResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetMoodDataResponse {


    @SerializedName("data")
    @Expose
    private var data: GetMoodDataResponseData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetMoodDataResponseData? {
        return data
    }

    fun setData(data: GetMoodDataResponseData?) {
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