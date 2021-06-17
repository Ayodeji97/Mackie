package com.example.android.danmack.repository

import com.example.android.danmack.network.SongApiService
import com.example.android.danmack.utils.Constants.API_HOST
import com.example.android.danmack.utils.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(private val songApiService: SongApiService) {

    /**
     * Directly from the api
     * */

    val id = "40008598"
    val locale = "en-US"
    suspend fun getAllSongs() = songApiService.getAllTopTrendSongs(API_KEY, API_HOST, id, locale)

}