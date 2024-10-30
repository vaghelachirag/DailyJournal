@file:Suppress("DEPRECATION")

package com.app.dailyjounral.view.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.app.dailyjounral.uttils.Session


open class BaseFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity
    private var progressDialog: ProgressDialog? = null
    lateinit var session: Session


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
        mActivity = context as Activity
        session = Session(mActivity)

    }

    fun showProgressbar() {
        hideProgressbar()
        if (progressDialog == null) {
            progressDialog = ProgressDialog(requireContext(), 1)
        }
        progressDialog?.show()
    }


     fun hideProgressbar() {
        if (isAdded&&progressDialog != null && progressDialog?.isShowing!!) progressDialog!!.dismiss()
    }

}
