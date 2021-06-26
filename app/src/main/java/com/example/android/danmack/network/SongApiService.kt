package com.example.android.danmack.network




import com.example.android.danmack.model.searchmodel.AutoComplete
import com.example.android.danmack.model.searchmodel.SearchData
import com.example.android.danmack.model.searchmodel.TermData
import com.example.android.danmack.model.songmodel.SongData
import kotlinx.coroutines.flow.Flow
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
        @Query("key") id : String,
        @Query("locale") locale : String
    ) : SongData


    @GET("auto-complete")
    suspend fun autoCompleteSearchSongs (
            @Header("x-rapidapi-key") api_key : String,
            @Header("x-rapidapi-host") host : String,
            @Query("term") term : String,
            @Query("locale") locale : String
    ) : AutoComplete


    @GET("search")
    suspend fun searchSongs (
            @Header("x-rapidapi-key") api_key : String,
            @Header("x-rapidapi-host") host : String,
            @Query("term") term : String,
            @Query("locale") locale : String,
            @Query("offset") offset : String,
            @Query("limit") limit : String
    ) : SearchData









}