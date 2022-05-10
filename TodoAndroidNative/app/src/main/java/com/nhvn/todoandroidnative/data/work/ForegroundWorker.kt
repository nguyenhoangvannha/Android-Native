package com.nhvn.todoandroidnative.data.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.nhvn.todoandroidnative.R
import kotlinx.coroutines.delay


class ForegroundWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    private val notificationManager = appContext.getSystemService(NotificationManager::class.java)

    private val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("Important background job")

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        Log.d(TAG, "Start job")

        createNotificationChannel()
        val notification = notificationBuilder.build()
        val foregroundInfo = ForegroundInfo(NOTIFICATION_ID, notification)
        setForeground(foregroundInfo)

        for (i in 0..100) {
            // we need it to get progress in UI
            setProgress(workDataOf(ARG_PROGRESS to i))
            // update the notification progress
            showProgress(i)
            delay(DELAY_DURATION)
        }

        Log.d(TAG, "Finish job")
        return Result.success()
    }

    private suspend fun showProgress(progress: Int) {
        val notification = notificationBuilder
            .setProgress(100, progress, false)
            .build()
        val foregroundInfo = ForegroundInfo(NOTIFICATION_ID, notification)
        setForeground(foregroundInfo)
    }


    override suspend fun getForegroundInfo(): ForegroundInfo {
        val notification = notificationBuilder.build()
        return ForegroundInfo(NOTIFICATION_ID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = notificationManager?.getNotificationChannel(CHANNEL_ID)
            if (notificationChannel == null) {
                notificationManager?.createNotificationChannel(
                    NotificationChannel(
                        CHANNEL_ID, TAG, NotificationManager.IMPORTANCE_LOW
                    )
                )
            }
        }
    }


    companion object {

        const val TAG = "ForegroundWorker"
        const val NOTIFICATION_ID = 42
        const val CHANNEL_ID = "Job progress"
        const val ARG_PROGRESS = "Progress"
        private const val DELAY_DURATION = 100L // ms
    }
}