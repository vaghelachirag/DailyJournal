package com.app.dailyjounral.model.getSocialLoginResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetSocialLoginData {

    @SerializedName("userId")
    @Expose
    private var userId: Int? = null

    @SerializedName("roleId")
    @Expose
    private var roleId: Int? = null

    @SerializedName("emailId")
    @Expose
    private var emailId: String? = null

    @SerializedName("fullName")
    @Expose
    private var fullName: String? = null

    @SerializedName("token")
    @Expose
    private var token: String? = null

    @SerializedName("isDeleted")
    @Expose
    private var isDeleted: Any? = null

    @SerializedName("isActive")
    @Expose
    private var isActive: Boolean? = null

    @SerializedName("isGoogleLoginEnabled")
    @Expose
    private var isGoogleLoginEnabled: Boolean? = null

    @SerializedName("isFacebookLoginEnabled")
    @Expose
    private var isFacebookLoginEnabled: Any? = null

    @SerializedName("message")
    @Expose
    private var message: Any? = null

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getRoleId(): Int? {
        return roleId
    }

    fun setRoleId(roleId: Int?) {
        this.roleId = roleId
    }

    fun getEmailId(): String? {
        return emailId
    }

    fun setEmailId(emailId: String?) {
        this.emailId = emailId
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getToken(): String? {
        return token
    }

    fun setToken(token: String?) {
        this.token = token
    }

    fun getIsDeleted(): Any? {
        return isDeleted
    }

    fun setIsDeleted(isDeleted: Any?) {
        this.isDeleted = isDeleted
    }

    fun getIsActive(): Boolean? {
        return isActive
    }

    fun setIsActive(isActive: Boolean?) {
        this.isActive = isActive
    }

    fun getIsGoogleLoginEnabled(): Boolean? {
        return isGoogleLoginEnabled
    }

    fun setIsGoogleLoginEnabled(isGoogleLoginEnabled: Boolean?) {
        this.isGoogleLoginEnabled = isGoogleLoginEnabled
    }

    fun getIsFacebookLoginEnabled(): Any? {
        return isFacebookLoginEnabled
    }

    fun setIsFacebookLoginEnabled(isFacebookLoginEnabled: Any?) {
        this.isFacebookLoginEnabled = isFacebookLoginEnabled
    }

    fun getMessage(): Any? {
        return message
    }

    fun setMessage(message: Any?) {
        this.message = message
    }

}