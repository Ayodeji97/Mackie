package com.example.android.danmack.di

import android.content.Context
import androidx.room.Room
import com.example.android.danmack.local.SongDao
import com.example.android.danmack.local.SongDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {


    @Singleton
    @Provides
    fun providesBlogDb (@ApplicationContext context: Context) : SongDatabase {
        var INSTANCE : SongDatabase? = null

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongDatabase::class.java,
                    SongDatabase.DATA_BASE
            ).build()

            INSTANCE = instance

            instance
        }
    }


    @Singleton
    @Provides
    fun provideBlogDao (songDatabase: SongDatabase) : SongDao {
        return songDatabase.songDao()
    }

}