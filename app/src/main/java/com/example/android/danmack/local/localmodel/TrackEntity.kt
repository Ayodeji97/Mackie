package com.example.android.danmack.local.localmodel

import com.example.android.danmack.model.Artist
import com.example.android.danmack.model.Hub
import com.example.android.danmack.model.ImagesX
import com.example.android.danmack.model.Share


data class TrackEntity (
        val artists: List<Artist>,
        val hub: Hub,
        val images: ImagesX,
        val key: String,
        val layout: String,
        val share: Share,
        val subtitle: String,
        val title: String,
        val type: String,
        val url: String
)