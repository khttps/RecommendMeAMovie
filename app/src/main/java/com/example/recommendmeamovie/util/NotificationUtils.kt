package com.example.recommendmeamovie.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.receiver.WatchedReceiver
import com.example.recommendmeamovie.ui.MainActivity
import com.example.recommendmeamovie.ui.movie.MovieFragmentArgs
import com.squareup.picasso.Picasso


private const val IMAGE_URL = "https://image.tmdb.org/t/p/w185"

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0


fun NotificationManager.sendNotification(context: Context, movie: MovieDetails) {

    val bundle = Bundle().apply {
            putString("movieName", movie.title)
            putLong("movieId", movie.id)
    }

    // PendingIntent Navigates to movie page when notification is clicked
    val contentIntent = NavDeepLinkBuilder(context)
        .setComponentName(MainActivity::class.java)
        .setGraph(R.navigation.navigation)
        .setDestination(R.id.movieFragment)
        .setArguments(bundle)
        .createPendingIntent()

    // Add movie to watched list when action is clicked
    val watchedIntent = Intent(context, WatchedReceiver::class.java)
    val watchedPendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, watchedIntent, FLAGS)

    // Movie Poster as Icon
    val largeIcon: Bitmap = Picasso.get().load(IMAGE_URL + movie.poster).get()

    val builder = NotificationCompat
        .Builder(context, context.getString(R.string.channel_id))
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(context.getString(R.string.notification_title))
        .setContentText(context.getString(R.string.notification_text, movie.title))
        .setLargeIcon(largeIcon)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(contentIntent)
        .addAction(R.drawable.ic_checkmark, "Yes, I've Seen It", watchedPendingIntent)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}

