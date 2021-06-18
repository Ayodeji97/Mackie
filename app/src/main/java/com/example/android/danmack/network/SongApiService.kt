package com.example.android.danmack.network



import com.example.android.danmack.model.SongData
import com.example.android.danmack.model.Track
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SongApiService {


    @GET("songs/list-artist-top-tracks")
    suspend fun getAllTopTrendSongs (
        @Header("x-rapidapi-key") api_key : String,
        @Header("x-rapidapi-host") host : String,
        @Query("id") id : String,
        @Query("locale") locale : String
    ) : SongData



    @GET("songs/list-recommendations")
    suspend fun getAllRecommendations (
        @Header("x-rapidapi-key") api_key : String,
        @Header("x-rapidapi-host") host : String,
        @Query("id") id : String,
        @Query("locale") locale : String
    ) : SongData
}