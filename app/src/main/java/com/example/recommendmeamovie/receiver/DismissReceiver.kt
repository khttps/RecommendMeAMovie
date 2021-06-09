package com.example.recommendmeamovie.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import javax.inject.Inject

class DismissReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancelAll()
    }
}