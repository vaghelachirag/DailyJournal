package com.app.dailyjounral.model.getGeneralEntityResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetGeneralEntityData {

    @SerializedName("journalEntriesOfGratitude")
    @Expose
    private var journalEntriesOfGratitude: List<GetJournalEntriesOfGratitude>? = null

    @SerializedName("otherJournalEntriesResponse")
    @Expose
    private var otherJournalEntriesResponse: GetOtherJournalEntriesResponse? = null

    fun getJournalEntriesOfGratitude(): List<GetJournalEntriesOfGratitude>? {
        return journalEntriesOfGratitude
    }

    fun setJournalEntriesOfGratitude(journalEntriesOfGratitude: List<GetJournalEntriesOfGratitude>?) {
        this.journalEntriesOfGratitude = journalEntriesOfGratitude
    }

    fun getOtherJournalEntriesResponse(): GetOtherJournalEntriesResponse? {
        return otherJournalEntriesResponse
    }

    fun setOtherJournalEntriesResponse(otherJournalEntriesResponse: GetOtherJournalEntriesResponse?) {
        this.otherJournalEntriesResponse = otherJournalEntriesResponse
    }

}