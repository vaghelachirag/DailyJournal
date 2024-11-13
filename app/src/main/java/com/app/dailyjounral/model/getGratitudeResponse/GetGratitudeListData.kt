package com.app.dailyjounral.model.getGratitudeResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetGratitudeListData {
    @SerializedName("gratitudeUserRecordId")
    @Expose
    private var gratitudeUserRecordId: Int? = null

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("answer")
    @Expose
    private var answer: String? = null

    @SerializedName("fullName")
    @Expose
    private var fullName: String? = null

    @SerializedName("gratitudeDate")
    @Expose
    private var gratitudeDate: String? = null

    @SerializedName("lastUpdateGratitudeDate")
    @Expose
    private var lastUpdateGratitudeDate: String? = null

    fun getGratitudeUserRecordId(): Int? {
        return gratitudeUserRecordId
    }

    fun setGratitudeUserRecordId(gratitudeUserRecordId: Int?) {
        this.gratitudeUserRecordId = gratitudeUserRecordId
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

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getGratitudeDate(): String? {
        return gratitudeDate
    }

    fun setGratitudeDate(gratitudeDate: String?) {
        this.gratitudeDate = gratitudeDate
    }

    fun getLastUpdateGratitudeDate(): String? {
        return lastUpdateGratitudeDate
    }

    fun setLastUpdateGratitudeDate(lastUpdateGratitudeDate: String?) {
        this.lastUpdateGratitudeDate = lastUpdateGratitudeDate
    }
}