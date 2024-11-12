package com.app.dailyjounral.model.getDailyReflectionResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDailyReflectionResponseData {

    @SerializedName("dailyReflectionUserRecordId")
    @Expose
    private var dailyReflectionUserRecordId: Int? = null

    @SerializedName("dailyReflectionId")
    @Expose
    private var dailyReflectionId: Int? = null

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("dailyReflectionDate")
    @Expose
    private var dailyReflectionDate: String? = null

    @SerializedName("question")
    @Expose
    private var question: String? = null

    @SerializedName("fullName")
    @Expose
    private var fullName: String? = null

    @SerializedName("answer")
    @Expose
    private var answer: Any? = null

    @SerializedName("totalRecords")
    @Expose
    private var totalRecords: Int? = null

    @SerializedName("rowNumber")
    @Expose
    private var rowNumber: Int? = null

    fun getDailyReflectionUserRecordId(): Int? {
        return dailyReflectionUserRecordId
    }

    fun setDailyReflectionUserRecordId(dailyReflectionUserRecordId: Int?) {
        this.dailyReflectionUserRecordId = dailyReflectionUserRecordId
    }

    fun getDailyReflectionId(): Int {
        return dailyReflectionId!!
    }

    fun setDailyReflectionId(dailyReflectionId: Int?) {
        this.dailyReflectionId = dailyReflectionId
    }

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getDailyReflectionDate(): String? {
        return dailyReflectionDate
    }

    fun setDailyReflectionDate(dailyReflectionDate: String?) {
        this.dailyReflectionDate = dailyReflectionDate
    }

    fun getQuestion(): String? {
        return question
    }

    fun setQuestion(question: String?) {
        this.question = question
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getAnswer(): Any? {
        return answer
    }

    fun setAnswer(answer: Any?) {
        this.answer = answer
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