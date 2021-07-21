package com.example.android.danmack.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
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
import com.example.android.danmack.utils.Constants.id
import com.example.android.danmack.utils.Constants.limit
import com.example.android.danmack.utils.Constants.locale
import com.example.android.danmack.utils.Constants.offset
import com.example.android.danmack.utils.Constants.recommendedId
import com.google.android.gms.common.wrappers.Wrappers.packageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
/**
 * Repository for all songs
 * */
@Singleton
class SongRepository @Inject constructor(
    private val songDatabase: SongDatabase,
    private val songApiService: SongApiService,
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher) {

    /**
     * Get Api key
     * */
    private val ai : ApplicationInfo = context.packageManager
        .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
   private val value = ai.metaData["keyValue"]
    val key = value.toString()


    /**
     * Top trend song list
     * */
    val allTopTrends : LiveData<List<Track>> =
           Transformations.map(songDatabase.songDao().getAllTracks()) {
               it.asTrackDomainModel()
           }


    /**
     * Recommendation song list
     * */
    val allRecommendations : LiveData<List<Track>> =
            Transformations.map(songDatabase.songDao().getRecommendedTracks()) {
                it.asTrackDomainModel()
            }


    /**
     * get tract selected by Id
     * */
   suspend fun getTrackSelectedById (id : String) : LocalTrackEntity? {
        return songDatabase.songDao().getTrackWithId(id)
    }



    /**
     * playlist song
     * */
  val allPlayLists : LiveData<List<Track>> = Transformations.map(songDatabase.songDao().getAllPlayListTracks()) {
      it.asTrackDomainModel()
  }


    /**
     * Update playlist song list
     * */
    suspend fun updatePlayList (localTrackEntity: LocalTrackEntity) {
        songDatabase.songDao().updatePlayList(localTrackEntity)
    }


    /**
     * refresh track song list
     * */
    suspend fun refreshTracks () {
        withContext(ioDispatcher) {
            try {

              val tracks = songApiService.getAllTopTrendSongs(key, API_HOST, id, locale)

                songDatabase.songDao().insertAllTracks(*tracks.tracks.asTrackDatabaseModel().toTypedArray())

            } catch (e : Exception) {
                Timber.e("ERROR INSERTING TRACKS : ${e.localizedMessage}")

            }
        }
    }



    /**
     * refresh recommended song list
     * */
    suspend fun refreshRecommendations () {
        withContext(ioDispatcher) {
            try {
                val recommendations = songApiService.getAllRecommendations(key, API_HOST, recommendedId, locale)
                songDatabase.songDao().insertAllTracks(*recommendations.tracks.asRecommendedDatabaseModel().toTypedArray())

            } catch (e : Exception) {
                Timber.e("ERROR INSERTING TRACKS : ${e.localizedMessage}")
            }
        }
    }



    /**
     * auto complete list
     * */
    suspend fun autoCompleteSearchSongs(query: String): AutoComplete {
        return songApiService.autoCompleteSearchSongs(key, API_HOST, query, locale)
    }


    /**
     * Search song
     * */
    suspend fun searchSongs(query: String) : SearchData {
            return  songApiService.searchSongs(key, API_HOST, query, locale, offset, limit)
    }

}