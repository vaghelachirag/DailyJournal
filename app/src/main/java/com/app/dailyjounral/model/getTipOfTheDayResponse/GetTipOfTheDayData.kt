package com.app.dailyjounral.model.getTipOfTheDayResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetTipOfTheDayData {


    @SerializedName("dailyTipId")
    @Expose
    private var dailyTipId: Int? = null

    @SerializedName("tipMessage")
    @Expose
    private var tipMessage: String? = null

    @SerializedName("tipDate")
    @Expose
    private var tipDate: String? = null

    @SerializedName("tipImage")
    @Expose
    private var tipImage: String? = null

    fun getDailyTipId(): Int? {
        return dailyTipId
    }

    fun setDailyTipId(dailyTipId: Int?) {
        this.dailyTipId = dailyTipId
    }

    fun getTipMessage(): String? {
        return tipMessage
    }

    fun setTipMessage(tipMessage: String?) {
        this.tipMessage = tipMessage
    }

    fun getTipDate(): String? {
        return tipDate
    }

    fun setTipDate(tipDate: String?) {
        this.tipDate = tipDate
    }

    fun getTipImage(): String? {
        return tipImage
    }

    fun setTipImage(tipImage: String?) {
        this.tipImage = tipImage
    }
}