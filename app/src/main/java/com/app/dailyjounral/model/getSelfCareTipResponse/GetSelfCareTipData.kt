package com.app.dailyjounral.model.getSelfCareTipResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetSelfCareTipData {

    @SerializedName("dailySelfCareTipId")
    @Expose
    private var dailySelfCareTipId: Int? = null

    @SerializedName("tipMessage")
    @Expose
    private var tipMessage: String? = null

    @SerializedName("tipDate")
    @Expose
    private var tipDate: String? = null

    @SerializedName("tipImage")
    @Expose
    private var tipImage: String? = null

    fun getDailySelfCareTipId(): Int? {
        return dailySelfCareTipId
    }

    fun setDailySelfCareTipId(dailySelfCareTipId: Int?) {
        this.dailySelfCareTipId = dailySelfCareTipId
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