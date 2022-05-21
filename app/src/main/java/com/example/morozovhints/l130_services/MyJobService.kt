package com.example.morozovhints.l130_services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.os.PersistableBundle
import android.util.Log
import kotlinx.coroutines.*


/**
 * JobService - наследник JobService.
 */
class MyJobService : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        log("onStartCommand")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            coroutineScope.launch {
                var workItem = params?.dequeueWork()
                while (workItem != null) {
                    val page = workItem.intent.getIntExtra(PAGE, 0)
                    for (i in 0..100) {
                        delay(1000)
                        log("Timer: $i Page: $page")
                    }
                    params?.completeWork(workItem)
                    workItem = params?.dequeueWork()
                    jobFinished(params, false)
                }
            }

        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        log("onStopJob")
        return true
    }

    private fun log(message: String) {
        Log.d("service_tag", "My service: $message")
    }

    companion object {
        const val JOB_ID = 10
        const val PAGE = "page"

        fun newBundle(pageNum: Int): PersistableBundle {
            return PersistableBundle().apply {
                putInt(PAGE, pageNum)
            }
        }

        fun newIntent(pageNum: Int): Intent {
            return Intent().apply {
                putExtra(PAGE, pageNum)
            }
        }
    }
}
