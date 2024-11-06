@file:Suppress("DEPRECATION")

package com.app.dailyjounral.view.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.app.dailyjounral.R
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.dialougs.ProgressDialog


open class BaseFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity
    private var progressDialog: ProgressDialog? = null
    private lateinit var session: Session


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
        mActivity = context as Activity
        session = Session(mActivity)

    }

    fun showProgressbar(message: String? = requireActivity().getString(R.string.please_wait)) {
        hideProgressbar()
        if (progressDialog == null) {
            progressDialog = ProgressDialog(requireContext(), message)
        }
        progressDialog?.show()
    }


    fun hideProgressbar() {
        if (isAdded&&progressDialog != null && progressDialog?.isShowing!!) progressDialog!!.dismiss()
    }

}
