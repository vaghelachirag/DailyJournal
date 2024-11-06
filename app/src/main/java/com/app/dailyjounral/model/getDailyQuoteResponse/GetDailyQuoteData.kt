package com.app.dailyjounral.model.getDailyQuoteResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyQuoteData {
    @SerializedName("dailyQuoteId")
    @Expose
    private var dailyQuoteId: Int? = null

    @SerializedName("quoteMessage")
    @Expose
    private var quoteMessage: String? = null

    @SerializedName("quoteDate")
    @Expose
    private var quoteDate: String? = null

    @SerializedName("quoteImage")
    @Expose
    private var quoteImage: String? = null

    fun getDailyQuoteId(): Int? {
        return dailyQuoteId
    }

    fun setDailyQuoteId(dailyQuoteId: Int?) {
        this.dailyQuoteId = dailyQuoteId
    }

    fun getQuoteMessage(): String? {
        return quoteMessage
    }

    fun setQuoteMessage(quoteMessage: String?) {
        this.quoteMessage = quoteMessage
    }

    fun getQuoteDate(): String? {
        return quoteDate
    }

    fun setQuoteDate(quoteDate: String?) {
        this.quoteDate = quoteDate
    }

    fun getQuoteImage(): String? {
        return quoteImage
    }

    fun setQuoteImage(quoteImage: String?) {
        this.quoteImage = quoteImage
    }
}