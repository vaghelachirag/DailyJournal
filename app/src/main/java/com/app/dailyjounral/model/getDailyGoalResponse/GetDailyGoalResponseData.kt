package com.app.dailyjounral.model.getDailyGoalResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyGoalResponseData {

    @SerializedName("dailyGoalId")
    @Expose
    private var dailyGoalId: Int? = null

    @SerializedName("goalMessage")
    @Expose
    private var goalMessage: String? = null

    @SerializedName("goalDate")
    @Expose
    private var goalDate: String? = null

    @SerializedName("goalImage")
    @Expose
    private var goalImage: String? = null

    fun getDailyGoalId(): Int? {
        return dailyGoalId
    }

    fun setDailyGoalId(dailyGoalId: Int?) {
        this.dailyGoalId = dailyGoalId
    }

    fun getGoalMessage(): String? {
        return goalMessage
    }

    fun setGoalMessage(goalMessage: String?) {
        this.goalMessage = goalMessage
    }

    fun getGoalDate(): String? {
        return goalDate
    }

    fun setGoalDate(goalDate: String?) {
        this.goalDate = goalDate
    }

    fun getGoalImage(): String? {
        return goalImage
    }

    fun setGoalImage(goalImage: String?) {
        this.goalImage = goalImage
    }


}