package com.example.android.danmack.local.localmodel

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.danmack.model.songmodel.ImagesX
import kotlinx.android.parcel.Parcelize
/**
 * Entity for song
 * */
@Parcelize
@Entity(tableName = "song_table")
data class LocalTrackEntity (
        @PrimaryKey(autoGenerate = false)
        val key: String,
        val title: String,
        val subtitle: String,
        val type: String,
        val url: String,
        @Embedded val localImages: ImagesX,
        val isRecommended : Boolean,
        var isPlayListSelected : Boolean
) : Parcelable