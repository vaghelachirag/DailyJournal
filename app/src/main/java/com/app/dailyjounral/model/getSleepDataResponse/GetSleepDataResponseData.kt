package com.app.dailyjounral.model.getSleepDataResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetSleepDataResponseData {

    @SerializedName("sleepUserRecordId")
    @Expose
    private var sleepUserRecordId: Int? = null

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("sleepDate")
    @Expose
    private var sleepDate: String? = null

    @SerializedName("sleepHour")
    @Expose
    private var sleepHour: Int? = null

    @SerializedName("fullName")
    @Expose
    private var fullName: String? = null

    @SerializedName("totalRecords")
    @Expose
    private var totalRecords: Int? = null

    @SerializedName("rowNumber")
    @Expose
    private var rowNumber: Int? = null

    fun getSleepUserRecordId(): Int? {
        return sleepUserRecordId
    }

    fun setSleepUserRecordId(sleepUserRecordId: Int?) {
        this.sleepUserRecordId = sleepUserRecordId
    }

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getSleepDate(): String? {
        return sleepDate
    }

    fun setSleepDate(sleepDate: String?) {
        this.sleepDate = sleepDate
    }

    fun getSleepHour(): Int? {
        return sleepHour
    }

    fun setSleepHour(sleepHour: Int?) {
        this.sleepHour = sleepHour
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