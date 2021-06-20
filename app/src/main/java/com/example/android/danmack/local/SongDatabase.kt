package com.example.android.danmack.local

import androidx.room.Database
import androidx.room.RoomDatabase


abstract class SongDatabase : RoomDatabase() {

    abstract fun songDao() : SongDao

    companion object {

        var DATA_BASE = "song_database"
    }

}