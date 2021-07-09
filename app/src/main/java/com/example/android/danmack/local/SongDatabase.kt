package com.example.android.danmack.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.danmack.local.localmodel.LocalTrackEntity

/**
 * Database for local song
 * */

@Database(entities = [LocalTrackEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class SongDatabase : RoomDatabase() {

    abstract fun songDao() : SongDao

    companion object {

        var DATA_BASE = "song_database"
    }

}