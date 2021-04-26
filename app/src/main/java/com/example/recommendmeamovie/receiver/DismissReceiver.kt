package com.example.recommendmeamovie.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import javax.inject.Inject

class DismissReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager.cancelAll()
    }
}