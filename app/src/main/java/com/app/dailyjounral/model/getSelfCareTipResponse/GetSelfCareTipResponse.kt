package com.app.dailyjounral.model.getSelfCareTipResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetSelfCareTipResponse {
    @SerializedName("data")
    @Expose
    private var data: GetSelfCareTipData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetSelfCareTipData? {
        return data
    }

    fun setData(data: GetSelfCareTipData?) {
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