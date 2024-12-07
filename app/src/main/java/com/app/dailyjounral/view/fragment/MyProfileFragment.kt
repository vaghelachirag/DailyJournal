package com.app.dailyjounral.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.FragmentMyProfileBinding
import com.app.dailyjounral.uttils.ImagePickerDialog
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.onItemClick
import com.app.dailyjounral.view.base.BaseFragment
import com.app.dailyjounral.view.base.menu.DashboardActivity
import com.app.dailyjounral.viewmodel.MyProfileViewModel
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.util.Date
import java.util.Objects

class MyProfileFragment: BaseFragment() {

    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!
    private val myProfileViewModel by lazy { MyProfileViewModel(activity as Context,binding,this@MyProfileFragment) }


    private var imgFile: File? = null
    private var imagePath: Uri? = null
    private val cameraCode: Int = 0x50
    private val galleryCode: Int = 0x51


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        binding.viewModel = myProfileViewModel
        binding.lifecycleOwner = this
        myProfileViewModel.init()


        binding.rbYes.isClickable = false;
        binding.rbNo.isClickable = false;

        binding.btnLogin.setOnClickListener {
         //   forgotPasswordViewModel.redirectToOTP()
        }

        myProfileViewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading && isAdded) showProgressbar()
            else if (!isLoading && isAdded) hideProgressbar()
        }

        binding.ivProfileImage.setOnClickListener {
            checkImagePickerPermission()
        }

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                @SuppressLint("RestrictedApi")
                override fun handleOnBackPressed() {
                  Log.e("Back","Back")
                    (context as DashboardActivity).navController.navigate(R.id.dashboardMenuFragment)
                }
            })

        return binding.root

    }

    // For Check Image permission
    private fun checkImagePickerPermission() {
        Dexter.withActivity(requireActivity())
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    // openImagePickerDialog()
                    openImagePickerDialog()
                  //  displayCamera()
                }

                override fun onPermissionRationaleShouldBeShown(permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?, token: PermissionToken?) {
                    token!!.continuePermissionRequest()
                }
            }).withErrorListener { }.onSameThread().check()
    }


    // For Check Image permission
    private fun openImagePickerDialog() {
        ImagePickerDialog(requireActivity(), object : onItemClick {
            override fun onCameraClicked() {
                displayCamera()
            }

            override fun onGalleryClicked() {
                val pickPhoto =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhoto, galleryCode)
            }
        }).show()

    }


    // Display Camera Pic
    fun displayCamera() {
        val destPath: String? = Objects.requireNonNull(Objects.requireNonNull(requireActivity()).getExternalFilesDir(null)!!).absolutePath
        val imagesFolder = File(destPath, this.resources.getString(R.string.app_name))
        try {
            imagesFolder.mkdirs()
            imgFile = File(imagesFolder, Date().time.toString() + ".jpg")
            imagePath = FileProvider.getUriForFile(requireActivity(), com.app.dailyjounral.BuildConfig.APPLICATION_ID + ".fileProvider", imgFile!!)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath)
            startActivityForResult(intent, cameraCode)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraCode && resultCode == Activity.RESULT_OK) {
            uploadImage()
        }
        if (requestCode == galleryCode && resultCode == Activity.RESULT_OK) {
            imagePath = Objects.requireNonNull(data!!).data
            imgFile = File(Objects.requireNonNull(imagePath?.let {
                Utility.getPath(requireActivity(), it)
            }))
            myProfileViewModel.imageFile.value = imgFile
            val filePath: String = imgFile!!.path
            val bitmap = BitmapFactory.decodeFile(filePath)
            Glide.with(context as Activity)
                .asBitmap()
                .load(bitmap)
                .circleCrop()
                .error(R.drawable.icon_placeholder)
                .placeholder(R.drawable.icon_placeholder)
                .into(binding.ivProfileImage)
        }
    }

    // For Upload Image
    private fun uploadImage() {
        if (imgFile != null) {
            val filePath: String = imgFile!!.path
            val bitmap = BitmapFactory.decodeFile(filePath)
           // myProfileViewModel.saveUpdateUserProfile(imgFile!!)
            myProfileViewModel.imageFile.value = imgFile

            Glide.with(context as Activity)
                .asBitmap()
                .load(bitmap)
                .circleCrop()
                .error(R.drawable.icon_placeholder)
                .placeholder(R.drawable.icon_placeholder)
                .into(binding.ivProfileImage)
        }

        requireFragmentManager().addOnBackStackChangedListener {
            val f: List<Fragment> = requireFragmentManager().fragments
            val frag: Fragment = f[0]
            Log.e("Data","Data")
        }
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                Log.e("OnBackPress","OnBackPres")
            }
        })
    }

    fun onBackPressed() {
        Log.e("OnBackPress","OnBackPres")
        (requireActivity() as DashboardActivity).navController.popBackStack(R.id.dashboardMenuFragment, true)
    }
}
