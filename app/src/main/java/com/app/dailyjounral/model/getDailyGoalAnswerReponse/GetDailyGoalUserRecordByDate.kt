package com.app.dailyjounral.model.getDailyGoalAnswerReponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyGoalUserRecordByDate {
    @SerializedName("dailyGoalUserRecordId")
    @Expose
    private var dailyGoalUserRecordId: Any? = null

    @SerializedName("userId")
    @Expose
    private var userId: Any? = null

    @SerializedName("dailyGoalDate")
    @Expose
    private var dailyGoalDate: Any? = null

    @SerializedName("isGoalCompleted")
    @Expose
    private var isGoalCompleted: Any? = null

    @SerializedName("isPreviousGoalReviewed")
    @Expose
    private var isPreviousGoalReviewed: Boolean? = null

    @SerializedName("answer")
    @Expose
    private var answer: String? = null

    fun getDailyGoalUserRecordId(): Any? {
        return dailyGoalUserRecordId
    }

    fun setDailyGoalUserRecordId(dailyGoalUserRecordId: Any?) {
        this.dailyGoalUserRecordId = dailyGoalUserRecordId
    }

    fun getUserId(): Any? {
        return userId
    }

    fun setUserId(userId: Any?) {
        this.userId = userId
    }

    fun getDailyGoalDate(): Any? {
        return dailyGoalDate
    }

    fun setDailyGoalDate(dailyGoalDate: Any?) {
        this.dailyGoalDate = dailyGoalDate
    }

    fun getIsGoalCompleted(): Any? {
        return isGoalCompleted
    }

    fun setIsGoalCompleted(isGoalCompleted: Any?) {
        this.isGoalCompleted = isGoalCompleted
    }

    fun getIsPreviousGoalReviewed(): Boolean? {
        return isPreviousGoalReviewed
    }

    fun setIsPreviousGoalReviewed(isPreviousGoalReviewed: Boolean?) {
        this.isPreviousGoalReviewed = isPreviousGoalReviewed
    }

    fun getAnswer(): String? {
        return answer
    }

    fun setAnswer(answer: String?) {
        this.answer = answer
    }


}