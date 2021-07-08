package com.example.android.danmack.ui.profile

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.android.danmack.databinding.FragmentProfileBinding
import com.example.android.danmack.utils.Constants
import com.example.android.danmack.utils.ProfileDialog
import com.example.android.danmack.utils.ProfileDialog.Companion.REQUEST_IMAGE_CAPTURE
import com.example.android.danmack.utils.SessionManager

class ProfileFragment : Fragment() {

    private lateinit var ui : FragmentProfileBinding
    private var photo_permission_string : String = ""
    private var gallery_permission_string : String = ""
    private lateinit var userEmail : String

    private val PICK_IMAGE = 100

    private lateinit var imageUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false)

        ui = FragmentProfileBinding.inflate(inflater)

        ui.profileFragmentChangePhoto.setOnClickListener {
          ProfileDialog().show(
                  childFragmentManager, ProfileDialog.TAG
          )
        }



        receiveUserResponseAndOpenCamera()

        receiveUserResponseAndChooseImageToUpload()


       userEmail = SessionManager.load(requireContext(), Constants.USEREMAIL)


        ui.profileFragmentEmailTv.text = userEmail


        return ui.root
    }


    private fun receiveUserResponseAndOpenCamera () {

        // We set the listener on the child fragmentManager

        childFragmentManager.setFragmentResultListener("photo_key", viewLifecycleOwner, FragmentResultListener {
            requestKey: String, bundle: Bundle ->

            photo_permission_string = bundle.getString("bundle_key").toString()

            if (photo_permission_string == "ask_photo_permission") {

                checkAndRequestCameraPermission()

            } else {
                Toast.makeText(requireContext(), "Please pick image", Toast.LENGTH_SHORT).show()
            }



        })

    }


    private fun receiveUserResponseAndChooseImageToUpload () {

        childFragmentManager.setFragmentResultListener("gallery_key", viewLifecycleOwner, FragmentResultListener {
            requestKey: String, bundle: Bundle ->

            gallery_permission_string = bundle.getString("gallery_bundle_key").toString()

            if (gallery_permission_string == "choose_photo_permission") {
                checkAndRequestWriteExternalStoragePermission()
            } else {
                Toast.makeText(requireContext(), "Please pick image", Toast.LENGTH_SHORT).show()
            }

        })
    }



    private fun checkAndRequestCameraPermission () {

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            -> {

                Toast.makeText(requireContext(), "Permission granted", Toast.LENGTH_SHORT).show()
                dispatchTakePictureIntent()
            }

            else -> photoLaunchPermission()
        }
    }

    private fun checkAndRequestWriteExternalStoragePermission () {

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
            -> {

                Toast.makeText(requireContext(), "Permission granted", Toast.LENGTH_SHORT).show()
                chooseImageFromGallery()
            }

            else -> chooseImageLaunchPermission()
        }
    }



    private val requestPhotoPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            dispatchTakePictureIntent()
        } else {
            photoLaunchPermission()
        }

    }

    private val requestGalleryPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {

        if (it) {
            chooseImageFromGallery()
        } else {
            chooseImageLaunchPermission()
        }

    }


    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    private fun chooseImageFromGallery () {
        val chooseImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(chooseImageIntent, PICK_IMAGE)

    }


    private fun photoLaunchPermission () {
        requestPhotoPermissionLauncher.launch(Manifest.permission.CAMERA)
    }


    private fun chooseImageLaunchPermission () {
        requestGalleryPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            Toast.makeText(requireContext(), "Profile picture updated", Toast.LENGTH_SHORT).show()
            ui.celebrityImage.setImageBitmap(imageBitmap)
        } else if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data!!
            Toast.makeText(requireContext(), "Profile picture updated", Toast.LENGTH_SHORT).show()
            ui.celebrityImage.setImageURI(imageUri)
        }

        else {
            Toast.makeText(requireContext(), "SEEEEEE", Toast.LENGTH_SHORT).show()
        }
    }





}


