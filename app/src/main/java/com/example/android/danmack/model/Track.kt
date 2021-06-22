package com.example.android.danmack.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Track(

    val images: ImagesX,
    val key: String,
    val subtitle: String,
    val title: String,
    val type: String,
    val url: String,

) : Parcelable
