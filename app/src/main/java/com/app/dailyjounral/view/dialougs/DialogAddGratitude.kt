package com.app.dailyjounral.view.dialougs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.DialougAddGratitudeBinding


class DialogAddGratitude(private var mContext: Context) : Dialog(mContext, R.style.ThemeDialog) {
    private lateinit var binding: DialougAddGratitudeBinding
    private var listener: OkButtonListener? = null

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
    }

    fun setListener(listener: OkButtonListener?): DialogAddGratitude {
        this.listener = listener
        return this
    }

    interface OkButtonListener {
        fun onOkPressed(dialog: DialogAddGratitude,  gratitude: String?,)
    }

}