package com.example.morozovhints.l130

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
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
 * ForeGround Service - для андроидов выше 26 версии необходимо использовать для постоянного
 * уведомления пользователя о том, что сервис активен. Его нельзя смахнуть или убрать.
 * В методе onCreate должен быть метод старта уведомления и пуска Foreground.
 * Данный метод при запуске ОБЯЗАН вызвать внутри сервиса startForeground, иначе приложение упадет.
 */
class MyForeGroundService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        createNotificationChannel()
        startForeground(NOTIFICATION_ID,createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 1..100) {
                delay(1000)
                log("Timer: $i")
            }
            //Вызов onDestroy. Это когда сервис нужно вызвать изнутри.
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
        TODO("Not yet implemented")
    }

    private fun log(message: String) {
        Log.d(FOREGROUND_LOG_TAG, "My service: $message")
    }

    /**
     * 1) Создаем канал для уведомлений.
     */
    private fun createNotificationChannel(){
//        1) Создаем менеджер уведомлений.           имя сервиса             приводим к менеджеру
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        2) Это нужно для апи >= 26. В низших версиях не нужно.
//         Фактически в новых версиях каждое уведомление должно привязываться к каналу.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            формируем канал уведомления
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
//            создаем канал
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
    /**
     * 2) Создаем уведомление.
     */
    private fun createNotification():Notification{
//      Создаем уведомление.
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
        const val FOREGROUND_LOG_TAG = "foreground_service_tag"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyForeGroundService::class.java).apply {
            }
        }
    }
}