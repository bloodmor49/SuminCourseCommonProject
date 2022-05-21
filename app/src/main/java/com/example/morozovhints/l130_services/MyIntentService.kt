package com.example.morozovhints.l130_services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.morozovhints.R

/**
 * Intent Service
 */
class MyIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        //Аналог START_REDELIVER_INTENT если true
        setIntentRedelivery(true)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID,createNotification())
    }

        override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        for (i in 1..10) {
            Thread.sleep(1000)
            log("Timer: $i")
        }
    }

    private fun log(message: String) {
        Log.d(FOREGROUND_LOG_TAG, "My service: $message")
    }


    private fun createNotificationChannel(){
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification():Notification{
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("ForGroundService ")
            .setContentText("Текст уведомления ForGroundService")
            .setSmallIcon(R.drawable.logo_okabe)
            .build()
    }

//    private fun showNotification(notificationManager:NotificationManager,notification:Notification) {
////      Добавляет уведомление в статус баре. id определяет уведомления. (новое или то же самое)
//        notificationManager.notify(1, notification)
//    }

    companion object {

        const val CHANNEL_ID = "channel_id"
        // появляется при долгом нажатии на уведомление. Можно отключать каналы.
        // в мессенджерах можно создать кучу каналов для разных штуках.
        const val CHANNEL_NAME = "channel_name"
        const val NOTIFICATION_ID = 1
        const val FOREGROUND_LOG_TAG = "intent_service_tag"
        const val NAME = "intent_service"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java).apply {
            }
        }
    }
}