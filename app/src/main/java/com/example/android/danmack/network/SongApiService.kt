package com.example.android.danmack.network




import com.example.android.danmack.model.searchmodel.AutoComplete
import com.example.android.danmack.model.searchmodel.SearchData
import com.example.android.danmack.model.searchmodel.TermData
import com.example.android.danmack.model.songmodel.SongData
import com.example.android.danmack.utils.Constants.AUTOCOMPLETE_ENDPOINT
import com.example.android.danmack.utils.Constants.RAPID_HOST
import com.example.android.danmack.utils.Constants.RAPID_KEY
import com.example.android.danmack.utils.Constants.RECOMMENDATION_ENDPOINT
import com.example.android.danmack.utils.Constants.SEARCH_ENDPOINT
import com.example.android.danmack.utils.Constants.TREND_ENDPOINT
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Api service containing all endpoint
 * */
interface SongApiService {


    @GET(TREND_ENDPOINT)
    suspend fun getAllTopTrendSongs (
        @Header(RAPID_KEY) api_key : String,
        @Header(RAPID_HOST) host : String,
        @Query("id") id : String,
        @Query("locale") locale : String
    ) : SongData


    @GET(RECOMMENDATION_ENDPOINT)
    suspend fun getAllRecommendations (
        @Header(RAPID_KEY) api_key : String,
        @Header(RAPID_HOST) host : String,
        @Query("key") id : String,
        @Query("locale") locale : String
    ) : SongData


    @GET(AUTOCOMPLETE_ENDPOINT)
    suspend fun autoCompleteSearchSongs (
            @Header(RAPID_KEY) api_key : String,
            @Header(RAPID_HOST) host : String,
            @Query("term") term : String,
            @Query("locale") locale : String
    ) : AutoComplete


    @GET(SEARCH_ENDPOINT)
    suspend fun searchSongs (
            @Header(RAPID_KEY) api_key : String,
            @Header(RAPID_HOST) host : String,
            @Query("term") term : String,
            @Query("locale") locale : String,
            @Query("offset") offset : String,
            @Query("limit") limit : String
    ) : SearchData

}