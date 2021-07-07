package com.example.android.danmack.utils

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Fragment.setTitle(title: String) {
    if (activity is AppCompatActivity) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }
}

fun Fragment.setDisplayHomeAsUpEnabled(bool: Boolean) {
    if (activity is AppCompatActivity) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
                bool
        )
    }
}

fun Fragment.playSongInBrowser (url : String) {

    val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)

    defaultBrowser.data = Uri.parse(url)

    startActivity(defaultBrowser)
}

fun Fragment.showAlertDialog (activity: FragmentActivity) {

    val builder = AlertDialog.Builder(activity)

    builder.setMessage("Camera permission needed for profile picture")
        .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

        })
        .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

        })

    builder.create()
}