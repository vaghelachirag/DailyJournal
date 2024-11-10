package com.app.dailyjounral.uttils

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.CommonDialogImagePickerBinding
import com.app.dailyjounral.databinding.DialogWithOkButtonBinding

class ImagePickerDialog(context: FragmentActivity?, private val itemClick: onItemClick) :
    AlertDialog(context) {

    private lateinit var binding: CommonDialogImagePickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.common_dialog_image_picker, null, false)
        setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            itemClick.onCameraClicked()
            dismiss()
        }

        binding.tvGallery.setOnClickListener {
            itemClick.onGalleryClicked()
            dismiss()
        }
    }
}

interface onItemClick {
    fun onCameraClicked()
    fun onGalleryClicked()
}