package com.example.recommendmeamovie.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchedReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onReceive(p0: Context?, p1: Intent?) {

        notificationManager.cancelAll()
        TODO("Not yet implemented")
    }
}