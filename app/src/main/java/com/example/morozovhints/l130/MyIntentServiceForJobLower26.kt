package com.example.morozovhints.l130

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.morozovhints.R
import kotlinx.coroutines.*


///////////////////////////////////СОБЕСЕДОВАНИЕ//////////////////////////////
//TODO() Сервисы: Какие значения может возвращаться метод onStartCommand.

/**
 * Intent Service - наследуется от интент сервиса.
 * При наследовании нужно указать имя сервиса.
 * Нужно переопределить onHandleIntent - код выполняется не в главном потоке.
 * (вместо onStartCommand в главном потоке).
 * Не нужно использовать корутины. Не нужен onBind. Не нужен onDestroy.
 * Сразу после выполнения метода - сервис сразу сам останавливается.
 * Все сервисы выполняются друг за другом, если вызвать несколько раз.
 * Запускать можно как обычный, так и форграунд сервис.
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