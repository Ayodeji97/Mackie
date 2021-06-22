package com.example.android.danmack.network.networkmodel

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.example.android.danmack.local.localmodel.LocalImagesXEntity
import com.example.android.danmack.model.ImagesX


data class NetworkTrackEntity (
        val key: String,
        val title: String,
        val subtitle: String,
        val type: String,
        val url: String,
        val networkImages: ImagesX
)