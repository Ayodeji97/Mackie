package com.example.android.danmack.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.danmack.di.IoDispatcher
import com.example.android.danmack.local.SongDatabase
import com.example.android.danmack.mapper.asRecommendedDatabaseModel
import com.example.android.danmack.mapper.asTrackDatabaseModel
import com.example.android.danmack.mapper.asTrackDomainModel
import com.example.android.danmack.model.Track
import com.example.android.danmack.network.SongApiService
import com.example.android.danmack.network.networkmodel.NetworkTrackEntity
import com.example.android.danmack.utils.Constants.API_HOST
import com.example.android.danmack.utils.Constants.API_KEY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(
       private val songDatabase: SongDatabase,
       private val songApiService: SongApiService,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher) {

    /**
     * Directly from the api
     * */

    val id = "40008598"
    val locale = "en-US"
    val recommendedId = "484129036"


    val allTopTrends : LiveData<List<Track>> =
           Transformations.map(songDatabase.songDao().getAllTracks()) {
               it.asTrackDomainModel()
           }


    val allRecommendations : LiveData<List<Track>> =
            Transformations.map(songDatabase.songDao().getRecommendedTracks()) {
                it.asTrackDomainModel()
            }




    suspend fun refreshTracks () {
        withContext(ioDispatcher) {
            try {

              val tracks = songApiService.getAllTopTrendSongs(API_KEY, API_HOST, id, locale)

                Log.i("SEEEE", "$tracks")

                songDatabase.songDao().insertAllTracks(*tracks.tracks.asTrackDatabaseModel().toTypedArray())

            } catch (e : Exception) {
                Timber.e("ERROR INSERTING TRACKS : ${e.localizedMessage}")

            }
        }
    }



    suspend fun refreshRecommendations () {
        withContext(ioDispatcher) {
            try {
                val recommendations = songApiService.getAllRecommendations(API_KEY, API_HOST, recommendedId, locale)
                songDatabase.songDao().insertAllTracks(*recommendations.tracks.asRecommendedDatabaseModel().toTypedArray())

            } catch (e : Exception) {
                Timber.e("ERROR INSERTING TRACKS : ${e.localizedMessage}")
            }
        }
    }

}