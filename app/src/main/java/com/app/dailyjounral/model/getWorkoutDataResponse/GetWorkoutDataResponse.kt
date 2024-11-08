package com.app.dailyjounral.model.getWorkoutDataResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetWorkoutDataResponse {


    @SerializedName("data")
    @Expose
    private var data: GetWorkoutResponseData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetWorkoutResponseData? {
        return data
    }

    fun setData(data: GetWorkoutResponseData?) {
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