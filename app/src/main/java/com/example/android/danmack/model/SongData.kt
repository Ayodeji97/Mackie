package com.example.android.danmack.model

import com.google.gson.annotations.SerializedName

data class SongData(
        @SerializedName("tracks")
        val tracks : List<Track>
)