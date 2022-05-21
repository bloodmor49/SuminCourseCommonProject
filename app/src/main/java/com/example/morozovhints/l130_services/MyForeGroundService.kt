package com.example.morozovhints.l130_services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.morozovhints.R
import kotlinx.coroutines.*

/**
 * ForeGround Service - сервис с постоянным уведомлением.
 */
class MyForeGroundService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val notificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    private val notificationBuilder by lazy {
        createNotificationBuilder()
    }

    var onProgressChanged: ((Int) -> Unit)? = null

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 1..100 step 5) {
                delay(1000)
                val notification = notificationBuilder
                    .setProgress(100, i, false)
                    .build()
                notificationManager.notify(NOTIFICATION_ID,notification)
                onProgressChanged?.invoke(i)
                log("Timer: $i")
            }
            stopSelf()
        }
        return START_STICKY
//        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        //Как только мы подпишемся, будет создан экземпляр LocalBinder
        return LocalBinder()
    }

    private fun log(message: String) {
        Log.d(FOREGROUND_LOG_TAG, "My service: $message")
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotificationBuilder(): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("ForGroundService ")
            .setContentText("Текст уведомления ForGroundService")
            .setSmallIcon(R.drawable.logo_okabe)
//            прогрессбар
            .setProgress(100, 0, false)
            .setOnlyAlertOnce(true)
    }

//    private fun showNotification(notificationManager:NotificationManager,notification:Notification) {
////      Добавляет уведомление в статус баре. id определяет уведомления. (новое или то же самое)
//        notificationManager.notify(1, notification)
//    }

    inner class LocalBinder: Binder(){
        fun getService() = this@MyForeGroundService
    }

    companion object {

        const val CHANNEL_ID = "channel_id"

        // появляется при долгом нажатии на уведомление. Можно отключать каналы.
        // в мессенджерах можно создать кучу каналов для разных штуках.
        const val CHANNEL_NAME = "channel_name"
        const val NOTIFICATION_ID = 1
        const val FOREGROUND_LOG_TAG = "foreground_service_tag"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyForeGroundService::class.java).apply {
            }
        }
    }
}