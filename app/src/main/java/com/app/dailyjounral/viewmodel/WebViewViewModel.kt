package com.app.dailyjounral.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.uttils.Session
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WebViewViewModel(private val context: Context): BaseViewModel() {
    var webViewURL = MutableLiveData<String>()

    // Session Manager
    var session = Session(context)

    fun init(context: Context, menuId: String) {
         webViewURL.value = "https://www.google.com/search?q=google&oq=google+&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIMCAEQIxgnGIAEGIoFMgYIAhAjGCcyDQgDEAAYkQIYgAQYigUyDQgEEAAYkQIYgAQYigUyBggFEEUYPDIGCAYQRRhBMgYIBxAFGEDSAQgzMTcwajBqN6gCALACAA&sourceid=chrome&ie=UTF-8"
    }

}