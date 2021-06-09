package com.example.recommendmeamovie.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.recommendmeamovie.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideChannel(@ApplicationContext context: Context) : NotificationChannel {
        return NotificationChannel(
            context.getString(R.string.channel_id),
            context.getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_DEFAULT)
            .apply {
                description = context.getString(R.string.channel_description)
            }
    }

    @Singleton
    @Provides
    fun provideManager(@ApplicationContext context: Context) : NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

}