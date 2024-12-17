package com.app.dailyjounral.model.getGeneralEntityResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetOtherJournalEntriesResponse {

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("goalAns")
    @Expose
    private var goalAns: String? = null

    @SerializedName("sleepHour")
    @Expose
    private var sleepHour: Int? = null

    @SerializedName("moodTypeId")
    @Expose
    private var moodTypeId: Int? = null

    @SerializedName("workoutTypeId")
    @Expose
    private var workoutTypeId: Int? = null

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getGoalAns(): String? {
        return goalAns
    }

    fun setGoalAns(goalAns: String?) {
        this.goalAns = goalAns
    }

    fun getSleepHour(): Int? {
        return sleepHour
    }

    fun setSleepHour(sleepHour: Int?) {
        this.sleepHour = sleepHour
    }

    fun getMoodTypeId(): Int? {
        return moodTypeId
    }

    fun setMoodTypeId(moodTypeId: Int?) {
        this.moodTypeId = moodTypeId
    }

    fun getWorkoutTypeId(): Int? {
        return workoutTypeId
    }

    fun setWorkoutTypeId(workoutTypeId: Int?) {
        this.workoutTypeId = workoutTypeId
    }
}