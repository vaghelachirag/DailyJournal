package com.app.dailyjounral.model.getDailyGoalResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyGoalResponse {

    @SerializedName("data")
    @Expose
    private var data: GetDailyGoalResponseData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetDailyGoalResponseData? {
        return data
    }

    fun setData(data: GetDailyGoalResponseData?) {
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