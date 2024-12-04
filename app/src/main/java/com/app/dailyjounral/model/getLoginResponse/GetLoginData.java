package com.app.dailyjounral.model.getLoginResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLoginData {
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("roleId")
    @Expose
    private Integer roleId;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("profilePicture")
    @Expose
    private String profilePicture;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("isGoogleLoginEnabled")
    @Expose
    private Boolean isGoogleLoginEnabled;
    @SerializedName("isFacebookLoginEnabled")
    @Expose
    private Object isFacebookLoginEnabled;
    @SerializedName("isChangePasswordScreeenToShow")
    @Expose
    private Boolean isChangePasswordScreeenToShow;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsGoogleLoginEnabled() {
        return isGoogleLoginEnabled;
    }

    public void setIsGoogleLoginEnabled(Boolean isGoogleLoginEnabled) {
        this.isGoogleLoginEnabled = isGoogleLoginEnabled;
    }

    public Object getIsFacebookLoginEnabled() {
        return isFacebookLoginEnabled;
    }

    public void setIsFacebookLoginEnabled(Object isFacebookLoginEnabled) {
        this.isFacebookLoginEnabled = isFacebookLoginEnabled;
    }

    public Boolean getIsChangePasswordScreeenToShow() {
        return isChangePasswordScreeenToShow;
    }

    public void setIsChangePasswordScreeenToShow(Boolean isChangePasswordScreeenToShow) {
        this.isChangePasswordScreeenToShow = isChangePasswordScreeenToShow;
    }


}
