package com.example.morozovhints.l130_services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService


/**
 * Job Intent Service
 */
class MyJobIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        log("onHandleWork")
        val page = intent.getIntExtra(PAGE, 0) ?: 0
        for (i in 1..10) {
            Thread.sleep(1000)
            log("Timer: $i Page: $page")
        }
    }


    private fun log(message: String) {
        Log.d(JOB_INTENT_LOG_TAG, "My service: $message")
    }

    companion object {

        const val JOB_INTENT_LOG_TAG = "job_intent_service_tag"
        const val PAGE = "page"
        const val JOB_ID = 11

        //запуск сервиса
        fun enqueue(context: Context,page: Int) {
            enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context,page)
            )
        }

        private fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}