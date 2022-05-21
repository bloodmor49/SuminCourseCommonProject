package com.example.morozovhints.l130_services

import android.app.*
import android.content.Context
import android.content.Intent
import android.util.Log



/**
 * Intent Service for job lower api 26
 */
class MyIntentServiceForJobLower26 : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        //Аналог START_REDELIVER_INTENT если true
        setIntentRedelivery(true)
    }


    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val page = intent?.getIntExtra(PAGE, 0) ?: 0
        for (i in 1..10) {
            Thread.sleep(1000)
            log("Timer: $i Page: $page")
        }
    }

    private fun log(message: String) {
        Log.d(FOREGROUND_LOG_TAG, "My service: $message")
    }

    companion object {

        const val FOREGROUND_LOG_TAG = "intent_service_tag"
        const val NAME = "intent_service"
        const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentServiceForJobLower26::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}