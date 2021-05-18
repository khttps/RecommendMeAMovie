package com.example.recommendmeamovie

import android.app.Application
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.example.recommendmeamovie.work.CacheWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application(), Configuration.Provider {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initializeCacheWorker()

    }

    private fun initializeCacheWorker() {
        val constraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.METERED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    setRequiresDeviceIdle(true)
            }.build()

        val workRequest = PeriodicWorkRequestBuilder<CacheWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        applicationScope.launch {
            WorkManager.getInstance(applicationContext)
                .enqueueUniquePeriodicWork(CacheWorker.WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, workRequest)
        }

    }
}