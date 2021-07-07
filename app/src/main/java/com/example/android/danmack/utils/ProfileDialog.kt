package com.example.android.danmack.utils

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.android.danmack.R
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class ProfileDialog : DialogFragment() {

    companion object {
        val REQUEST_IMAGE_CAPTURE = 1
        const val TAG = "ProfileDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.choose_photo_title)
                .setItems(R.array.photo_option,
                    DialogInterface.OnClickListener { dialog, id ->
                        when(id) {
                            0 -> takePhotoSignal()
                            1 -> choosePhotoSignal()
                        }
                    })

//                .setNegativeButton(R.string.cancel,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // User cancelled the dialog
//                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


    private fun takePhotoSignal () {
        val photoSignal = "ask_photo_permission"

        setFragmentResult("photo_key", bundleOf("bundle_key" to photoSignal))
    }


    private fun choosePhotoSignal () {

        val choosePhotoSignal = "choose_photo_permission"

        setFragmentResult("gallery_key", bundleOf("gallery_bundle_key" to choosePhotoSignal))
    }










}