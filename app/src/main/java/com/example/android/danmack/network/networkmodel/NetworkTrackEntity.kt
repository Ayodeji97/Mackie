package com.example.android.danmack.network.networkmodel

import com.example.android.danmack.model.songmodel.ImagesX


data class NetworkTrackEntity (
        val key: String,
        val title: String,
        val subtitle: String,
        val type: String,
        val url: String,
        val networkImages: ImagesX
)