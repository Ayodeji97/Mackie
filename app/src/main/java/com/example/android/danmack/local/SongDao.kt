package com.example.android.danmack.local
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.danmack.local.localmodel.LocalTrackEntity

@Dao
interface SongDao {


    /**
     * Insert Track into the db
     * */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTracks (vararg localTrackEntity: LocalTrackEntity)


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

    @Query("DELETE FROM song_table")
    suspend fun clear()



}