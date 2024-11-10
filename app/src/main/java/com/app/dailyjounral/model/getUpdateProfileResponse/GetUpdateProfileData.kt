package com.app.dailyjounral.model.getUpdateProfileResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetUpdateProfileData {
    @SerializedName("userImageName")
    @Expose
    private var userImageName: Any? = null

    @SerializedName("statusOfInsertUpdate")
    @Expose
    private var statusOfInsertUpdate: Any? = null

    fun getUserImageName(): Any? {
        return userImageName
    }

    fun setUserImageName(userImageName: Any?) {
        this.userImageName = userImageName
    }

    fun getStatusOfInsertUpdate(): Any? {
        return statusOfInsertUpdate
    }

    fun setStatusOfInsertUpdate(statusOfInsertUpdate: Any?) {
        this.statusOfInsertUpdate = statusOfInsertUpdate
    }

}