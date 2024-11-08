package com.app.dailyjounral.model.getWorkoutDataResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetWorkoutResponseData {
    @SerializedName("workoutUserRecordId")
    @Expose
    private var workoutUserRecordId: Int? = null

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("workoutDate")
    @Expose
    private var workoutDate: String? = null

    @SerializedName("workoutTypeId")
    @Expose
    private var workoutTypeId: Int? = null

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

    fun getWorkoutDate(): String? {
        return workoutDate
    }

    fun setWorkoutDate(workoutDate: String?) {
        this.workoutDate = workoutDate
    }

    fun getWorkoutTypeId(): Int? {
        return workoutTypeId
    }

    fun setWorkoutTypeId(workoutTypeId: Int?) {
        this.workoutTypeId = workoutTypeId
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