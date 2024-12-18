package com.app.dailyjounral

import android.app.Application


class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
      /*  FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)*/
    }
}