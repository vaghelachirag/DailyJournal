package com.app.dailyjounral.model.getGeneralEntityResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetGeneralEntityResponse {

    @SerializedName("data")
    @Expose
    private var data: GetGeneralEntityData? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getData(): GetGeneralEntityData? {
        return data
    }

    fun setData(data: GetGeneralEntityData?) {
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