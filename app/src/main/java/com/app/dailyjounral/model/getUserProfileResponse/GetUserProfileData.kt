package com.app.dailyjounral.model.getUserProfileResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetUserProfileData {

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("fullName")
    @Expose
    private var fullName: String? = null

    @SerializedName("emailId")
    @Expose
    private var emailId: String? = null

    @SerializedName("isAdult")
    @Expose
    private var isAdult: Boolean? = null

    @SerializedName("profilePicture")
    @Expose
    private var profilePicture: String? = null

    @SerializedName("isActive")
    @Expose
    private var isActive: Boolean? = null

    @SerializedName("totalRecords")
    @Expose
    private var totalRecords: Any? = null

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getEmailId(): String? {
        return emailId
    }

    fun setEmailId(emailId: String?) {
        this.emailId = emailId
    }

    fun getIsAdult(): Boolean? {
        return isAdult
    }

    fun setIsAdult(isAdult: Boolean?) {
        this.isAdult = isAdult
    }

    fun getProfilePicture(): String? {
        return profilePicture
    }

    fun setProfilePicture(profilePicture: String?) {
        this.profilePicture = profilePicture
    }

    fun getIsActive(): Boolean? {
        return isActive
    }

    fun setIsActive(isActive: Boolean?) {
        this.isActive = isActive
    }

    fun getTotalRecords(): Any? {
        return totalRecords
    }

    fun setTotalRecords(totalRecords: Any?) {
        this.totalRecords = totalRecords
    }
}