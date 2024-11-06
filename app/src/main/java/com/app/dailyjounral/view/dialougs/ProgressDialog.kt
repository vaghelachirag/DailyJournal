package com.app.dailyjounral.view.dialougs

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.DialogProgressBinding


class ProgressDialog @JvmOverloads constructor(
    context: Context,
    private var message: String? = null
) :
    AlertDialog(context, R.style.ProgressDialog) {
    private lateinit var binding: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_progress,
            null,
            false
        )
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        binding.message = message
    }
}
