package com.example.android.danmack.local.localmodel

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface RecommendationDao {

    /**
     * Insert Track into the db
     * */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecommendations (vararg localTrackEntity: LocalTrackEntity)


    /**
     * Get a specific Track
     * */
    @Query("SELECT * FROM recommendation_table WHERE `key` = :id")
    suspend fun getRecommendationsWithId (id : String) : LocalTrackEntity?

    /**
     * Get all tracks
     * */

    @Query("SELECT * FROM recommendation_table")
    fun getAllRecommendations () : LiveData<List<LocalTrackEntity>>

    @Query("DELETE FROM song_table")
    suspend fun clear()

}