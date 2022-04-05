package com.nhvn.todoandroidnative.data.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

private val KEY: String = "WORK_RESULT";

class Worker1(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val value1 = "A"
        val value2 = "B"
        return try {
            val result = value1 + value2
            val data = workDataOf(Pair(KEY, result))
            Result.success(data)
        } catch (error: Error) {
            Result.failure()
        }

    }
}

class Worker2(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val input = inputData.getString(KEY)
        val value1 = "C"
        val value2 = "D"
        return try {
            val result = input + value1 + value2
            val data = workDataOf(Pair(KEY, result))
            Result.success(data)
        } catch (error: Error) {
            Result.failure()
        }

    }
}

class Worker3(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val input = inputData.getString(KEY)
        val value1 = "E"
        val value2 = "F"
        return try {
            val result = input + value1 + value2

            Log.i("Work chain result", result)

            val data = workDataOf(Pair(KEY, result))
            Result.success(data)
        } catch (error: Error) {
            Result.failure()
        }

    }
}