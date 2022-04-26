package com.nhvn.todoandroidnative.data

import android.app.*
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.nhvn.todoandroidnative.R
import com.nhvn.todoandroidnative.ui.elements.MainActivity

class ForegroundService : Service() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val input = intent.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, FLAG_IMMUTABLE
        )
        val notification: Notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }
}