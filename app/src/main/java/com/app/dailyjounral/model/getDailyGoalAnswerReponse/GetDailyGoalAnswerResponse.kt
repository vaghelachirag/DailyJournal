package com.app.dailyjounral.model.getDailyGoalAnswerReponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyGoalAnswerResponse {

    @SerializedName("data")
    @Expose
    private var data: GetDailyGoalAnswerData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetDailyGoalAnswerData? {
        return data
    }

    fun setData(data: GetDailyGoalAnswerData?) {
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