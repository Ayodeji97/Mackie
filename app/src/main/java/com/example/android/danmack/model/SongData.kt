package com.example.android.danmack.model

import com.example.android.danmack.network.networkmodel.NetworkTrackEntity
import com.google.gson.annotations.SerializedName

data class SongData(
        val tracks : List<Track>
)