package com.example.morozovhints.l130_services

import android.content.Context
import android.util.Log
import androidx.work.*


/**
 * WorkManager
 */
class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0) ?: 0
        for (i in 1..10) {
            Thread.sleep(1000)
            log("Timer: $i Page: $page")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d(WORKER_LOG_TAG, "My service: $message")
    }

    companion object {
        const val WORKER_LOG_TAG = "worker"
        const val PAGE = "page"
        const val WORK_NAME = "work name"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(PAGE to page))
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints(): Constraints {
            return Constraints.Builder()
                .setRequiresDeviceIdle(true)
                .build()
        }
    }
}