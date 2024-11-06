package com.app.dailyjounral.model.getTipOfTheDayResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetTipOfTheDayResponse {

    @SerializedName("data")
    @Expose
    private var data: GetTipOfTheDayData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetTipOfTheDayData? {
        return data
    }

    fun setData(data: GetTipOfTheDayData?) {
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