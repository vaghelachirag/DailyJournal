package com.app.dailyjounral.view.dialougs

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.DialougAddGratitudeBinding
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils


class DialogAddGratitude(private var mContext: Context, private var isFromAdd: Boolean, private var gratitudeAnswer: String) : Dialog(mContext, R.style.ThemeDialog) {
    private lateinit var binding: DialougAddGratitudeBinding
    private var listener: OkButtonListener? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialoug_add_gratitude, null, false)

        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            listener?.onOkPressed(this, binding.edtGratitude.text.toString())
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        if (isFromAdd){
            binding.btnOk.text = "Add"
        }
        else{
            binding.btnOk.text = "Update"
            binding.edtGratitude.setText(gratitudeAnswer)
        }
        binding.txtCurrentDate.text = Utils().getCurrentDate()
    }

    fun setListener(listener: OkButtonListener?): DialogAddGratitude {
        this.listener = listener
        return this
    }

    interface OkButtonListener {
        fun onOkPressed(dialog: DialogAddGratitude,  gratitude: String?,)
    }

}