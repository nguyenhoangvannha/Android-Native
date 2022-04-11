package com.nhvn.todoandroidnative.data.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        return try {
            // Do the work here--in this case, upload the images.
            Log.i("UploadWorker", Date().toString())

            // Indicate whether the work finished successfully with the Result
            Result.success()
        } catch (error: Error) {
            Result.failure()
        }

    }
}