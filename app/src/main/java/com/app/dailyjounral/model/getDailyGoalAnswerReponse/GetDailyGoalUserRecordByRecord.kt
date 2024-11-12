package com.app.dailyjounral.model.getDailyGoalAnswerReponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyGoalUserRecordByRecord {

    @SerializedName("dailyGoalUserRecordId")
    @Expose
    private var dailyGoalUserRecordId: Int? = null

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("answer")
    @Expose
    private var answer: String? = null

    @SerializedName("dailyGoalDate")
    @Expose
    private var dailyGoalDate: String? = null

    @SerializedName("isGoalCompleted")
    @Expose
    private var isGoalCompleted: Any? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    fun getDailyGoalUserRecordId(): Int? {
        return dailyGoalUserRecordId
    }

    fun setDailyGoalUserRecordId(dailyGoalUserRecordId: Int?) {
        this.dailyGoalUserRecordId = dailyGoalUserRecordId
    }

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getAnswer(): String? {
        return answer
    }

    fun setAnswer(answer: String?) {
        this.answer = answer
    }

    fun getDailyGoalDate(): String? {
        return dailyGoalDate
    }

    fun setDailyGoalDate(dailyGoalDate: String?) {
        this.dailyGoalDate = dailyGoalDate
    }

    fun getIsGoalCompleted(): Any? {
        return isGoalCompleted
    }

    fun setIsGoalCompleted(isGoalCompleted: Any?) {
        this.isGoalCompleted = isGoalCompleted
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }
}