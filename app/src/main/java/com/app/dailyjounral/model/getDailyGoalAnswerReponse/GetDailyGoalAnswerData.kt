package com.app.dailyjounral.model.getDailyGoalAnswerReponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyGoalAnswerData {

    @SerializedName("dailyGoalUserRecordByDate")
    @Expose
    private var dailyGoalUserRecordByDate: GetDailyGoalUserRecordByDate? = null

    @SerializedName("dailyGoalUserRecord")
    @Expose
    private var dailyGoalUserRecord: GetDailyGoalUserRecordByRecord? = null

    fun getDailyGoalUserRecordByDate(): GetDailyGoalUserRecordByDate? {
        return dailyGoalUserRecordByDate
    }

    fun setDailyGoalUserRecordByDate(dailyGoalUserRecordByDate: GetDailyGoalUserRecordByDate?) {
        this.dailyGoalUserRecordByDate = dailyGoalUserRecordByDate
    }

    fun getDailyGoalUserRecord(): GetDailyGoalUserRecordByRecord? {
        return dailyGoalUserRecord
    }

    fun setDailyGoalUserRecord(dailyGoalUserRecord: GetDailyGoalUserRecordByRecord?) {
        this.dailyGoalUserRecord = dailyGoalUserRecord
    }
}