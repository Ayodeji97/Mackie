package com.example.android.danmack.local.localmodel

import androidx.room.Entity
import com.example.android.danmack.model.Track

@Entity(tableName = "song_table")
data class SongDataEntity (
        val tracks : List<Track>
)