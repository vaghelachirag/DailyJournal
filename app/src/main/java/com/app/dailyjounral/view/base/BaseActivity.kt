package com.app.dailyjounral.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.dailyjounral.R
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.dialougs.ProgressDialog


open class BaseActivity : AppCompatActivity() {
    private lateinit var session: Session
    private var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = Session(this)
    }
    fun showProgressbar(message: String? = getString(R.string.please_wait)) {
        hideProgressbar()
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this, message)
        }
        progressDialog?.show()
    }


    fun hideProgressbar() {
        if (progressDialog != null && progressDialog?.isShowing!!) progressDialog!!.dismiss()
    }

}
