package com.example.android.danmack.repository

import com.example.android.danmack.model.SongData
import com.example.android.danmack.model.Track
import com.example.android.danmack.network.SongApiService
import com.example.android.danmack.utils.Constants.API_HOST
import com.example.android.danmack.utils.Constants.API_KEY
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(private val songApiService: SongApiService) {

    /**
     * Directly from the api
     * */

    val id = "40008598"
    val locale = "en-US"
    suspend fun getAllSongs() : SongData {

        Timber.i("REPOST: ${songApiService.getAllTopTrendSongs(API_KEY, API_HOST, id, locale)}")

        return songApiService.getAllTopTrendSongs(API_KEY, API_HOST, id, locale)
    }


}