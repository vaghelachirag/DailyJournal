package com.app.dailyjounral.model.getGratitudeResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetGratitudeListResponse {

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: ArrayList<GetGratitudeListData>? = null

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

    fun getData(): ArrayList<GetGratitudeListData>? {
        return data
    }

    fun setData(data: ArrayList<GetGratitudeListData>?) {
        this.data = data
    }
}