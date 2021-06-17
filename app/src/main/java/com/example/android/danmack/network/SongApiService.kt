package com.example.android.danmack.network


import com.example.android.danmack.model.Song
import com.example.android.danmack.utils.Constants.API_KEY
import com.example.android.danmack.utils.Constants.BASE_URL
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface SongApiService {


    @GET("songs/list-artist-top-tracks")
    suspend fun getAllTopTrendSongs (
        @Header("x-rapidapi-key") api_key : String,
        @Header("x-rapidapi-host") host : String,
        @Query("id") id : String,
        @Query("locale") locale : String
    ) : Song

    @GET("songs/list-recommendations")
    suspend fun getAllRecommendations (
        @Header("x-rapidapi-key") api_key : String,
        @Header("x-rapidapi-host") host : String,
        @Query("id") id : String,
        @Query("locale") locale : String
    ) : Song


}