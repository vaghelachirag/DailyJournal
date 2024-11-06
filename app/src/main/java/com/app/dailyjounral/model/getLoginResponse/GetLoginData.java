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
    private Object fullName;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("tokenCreatedDate")
    @Expose
    private String tokenCreatedDate;

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

    public Object getFullName() {
        return fullName;
    }

    public void setFullName(Object fullName) {
        this.fullName = fullName;
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

    public String getTokenCreatedDate() {
        return tokenCreatedDate;
    }

    public void setTokenCreatedDate(String tokenCreatedDate) {
        this.tokenCreatedDate = tokenCreatedDate;
    }

}
