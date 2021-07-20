package com.example.android.danmack.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.danmack.repository.SongRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException

/**
 * Work class for periodic sync of data between remote data source and local data source
 * */
@HiltWorker
class RefreshDataWork @AssistedInject constructor(
    @Assisted appContext : Context,
    @Assisted workerParams : WorkerParameters,
    private val songRepository: SongRepository
) : CoroutineWorker(appContext, workerParams) {

    /**
     * [doWork] is a suspend func and it will run on the background thread
     * */
    override suspend fun doWork(): Result {
       return try {
            songRepository.refreshRecommendations()
            songRepository.refreshTracks()
            Result.success()
        } catch (e : HttpException) {

            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

}