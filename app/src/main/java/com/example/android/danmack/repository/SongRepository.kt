package com.example.android.danmack.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.danmack.di.IoDispatcher
import com.example.android.danmack.local.SongDatabase
import com.example.android.danmack.local.localmodel.LocalTrackEntity
import com.example.android.danmack.mapper.asRecommendedDatabaseModel
import com.example.android.danmack.mapper.asTrackDatabaseModel
import com.example.android.danmack.mapper.asTrackDomainModel
import com.example.android.danmack.model.searchmodel.AutoComplete
import com.example.android.danmack.model.searchmodel.SearchData
import com.example.android.danmack.model.songmodel.Track
import com.example.android.danmack.network.SongApiService
import com.example.android.danmack.utils.Constants.API_HOST
import com.example.android.danmack.utils.Constants.API_KEY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
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
    val offset = "0"
    val limit = "5"


    val allTopTrends : LiveData<List<Track>> =
           Transformations.map(songDatabase.songDao().getAllTracks()) {
               it.asTrackDomainModel()
           }


    val allRecommendations : LiveData<List<Track>> =
            Transformations.map(songDatabase.songDao().getRecommendedTracks()) {
                it.asTrackDomainModel()
            }

   suspend fun getTrackSelectedById (id : String) : LocalTrackEntity? {
        return songDatabase.songDao().getTrackWithId(id)
    }


  val allPlayLists : LiveData<List<Track>> = Transformations.map(songDatabase.songDao().getAllPlayListTracks()) {
      it.asTrackDomainModel()
  }

    suspend fun updatePlayList (localTrackEntity: LocalTrackEntity) {
        songDatabase.songDao().updatePlayList(localTrackEntity)
    }

    //fun getAllPlayLists () : LiveData<List<LocalTrackEntity>> = songDatabase.songDao().getAllPlayListTracks()


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


    suspend fun autoCompleteSearchSongs(query: String): AutoComplete {
        return songApiService.autoCompleteSearchSongs(API_KEY, API_HOST, query, locale)
    }


    suspend fun searchSongs(query: String) : SearchData {
            return  songApiService.searchSongs(API_KEY, API_HOST, query, locale, offset, limit)
    }




}