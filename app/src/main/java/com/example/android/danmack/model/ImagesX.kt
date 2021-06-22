package com.example.android.danmack.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImagesX(
    val background: String,
    val coverart: String,
    val coverarthq: String,
    val joecolor: String
) : Parcelable