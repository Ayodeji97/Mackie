package com.example.android.danmack.local

import com.example.android.danmack.model.ArtistsHits
import com.example.android.danmack.model.TracksHits


data class SongEntity (
    val tracks: TracksHits,
    val artists: ArtistsHits

)

