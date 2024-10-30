package com.app.dailyjounral.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.dailyjounral.uttils.Session



open class BaseActivity : AppCompatActivity() {
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = Session(this)
    }

}
