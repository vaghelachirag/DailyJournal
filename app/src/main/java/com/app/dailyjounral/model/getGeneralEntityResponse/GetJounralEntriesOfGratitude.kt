package com.app.dailyjounral.model.getGeneralEntityResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




class GetJournalEntriesOfGratitude {

    @SerializedName("gratitudeAns")
    @Expose
    private var gratitudeAns: String? = null

    fun getGratitudeAns(): String? {
        return gratitudeAns
    }

    fun setGratitudeAns(gratitudeAns: String?) {
        this.gratitudeAns = gratitudeAns
    }
}