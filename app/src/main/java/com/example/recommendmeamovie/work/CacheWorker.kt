package com.example.recommendmeamovie.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.recommendmeamovie.repository.MovieRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CacheWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val movieRepository: MovieRepository)
    : CoroutineWorker(appContext, workerParameters) {

    companion object {
        const val WORK_NAME = "cache_work"
    }

    override suspend fun doWork(): Result {
        movieRepository.refreshCacheData()
        return Result.success()
    }
}