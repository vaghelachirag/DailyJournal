package com.app.dailyjounral.model.getMoodResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetMoodDataResponseData {

    @SerializedName("workoutUserRecordId")
    @Expose
    private var workoutUserRecordId: Int? = null

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("moodDate")
    @Expose
    private var moodDate: String? = null

    @SerializedName("moodTypeId")
    @Expose
    private var moodTypeId: Int? = null

    @SerializedName("fullName")
    @Expose
    private var fullName: String? = null

    @SerializedName("totalRecords")
    @Expose
    private var totalRecords: Int? = null

    @SerializedName("rowNumber")
    @Expose
    private var rowNumber: Int? = null

    fun getWorkoutUserRecordId(): Int? {
        return workoutUserRecordId
    }

    fun setWorkoutUserRecordId(workoutUserRecordId: Int?) {
        this.workoutUserRecordId = workoutUserRecordId
    }

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getMoodDate(): String? {
        return moodDate
    }

    fun setMoodDate(moodDate: String?) {
        this.moodDate = moodDate
    }

    fun getMoodTypeId(): Int? {
        return moodTypeId
    }

    fun setMoodTypeId(moodTypeId: Int?) {
        this.moodTypeId = moodTypeId
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getTotalRecords(): Int? {
        return totalRecords
    }

    fun setTotalRecords(totalRecords: Int?) {
        this.totalRecords = totalRecords
    }

    fun getRowNumber(): Int? {
        return rowNumber
    }

    fun setRowNumber(rowNumber: Int?) {
        this.rowNumber = rowNumber
    }

}