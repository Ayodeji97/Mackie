package com.example.android.danmack.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.danmack.local.localmodel.LocalTrackEntity

/**
 * Data access object
 * */
@Dao
interface SongDao {


    /**
     * Insert Track into the db
     * */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTracks (vararg localTrackEntity: LocalTrackEntity)


    /**
     * Update
     * */

    @Update
    suspend fun updatePlayList (localTrackEntity: LocalTrackEntity)


    /**
     * Get a specific Track
     * */
    @Query("SELECT * FROM song_table WHERE `key` = :id")
   suspend fun getTrackWithId (id : String) : LocalTrackEntity?

    /**
     * Get all tracks
     * */

    @Query("SELECT * FROM song_table WHERE isRecommended = 0")
    fun getAllTracks () : LiveData<List<LocalTrackEntity>>

    @Query("SELECT * FROM song_table WHERE isRecommended = 1")
    fun getRecommendedTracks () : LiveData<List<LocalTrackEntity>>

    @Query("SELECT * FROM song_table WHERE isPlayListSelected = 1")
    fun getAllPlayListTracks () : LiveData<List<LocalTrackEntity>>


    @Query("DELETE FROM song_table")
    suspend fun clear()



}