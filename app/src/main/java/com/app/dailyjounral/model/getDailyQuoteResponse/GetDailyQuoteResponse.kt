package com.app.dailyjounral.model.getDailyQuoteResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyQuoteResponse {

    @SerializedName("data")
    @Expose
    private var data: GetDailyQuoteData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetDailyQuoteData? {
        return data
    }

    fun setData(data: GetDailyQuoteData?) {
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