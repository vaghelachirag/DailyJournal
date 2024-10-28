package com.app.dailyjounral.view.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.dailyjounral.uttils.Session



open class BaseActivity : AppCompatActivity() {
    private var shouldPerformDispatchTouch = true
    private lateinit var session: Session
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = Session(this)
    }

}
