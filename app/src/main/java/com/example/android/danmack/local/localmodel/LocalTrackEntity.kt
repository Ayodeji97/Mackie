package com.example.android.danmack.local.localmodel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.danmack.model.ImagesX

@Entity(tableName = "song_table")
data class LocalTrackEntity (
        @PrimaryKey(autoGenerate = false)
        val key: String,
        val title: String,
        val subtitle: String,
        val type: String,
        val url: String,
        @Embedded val localImages: ImagesX,
        val isRecommended : Boolean
)