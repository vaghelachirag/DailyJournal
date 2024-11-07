package com.app.dailyjounral.model.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

}