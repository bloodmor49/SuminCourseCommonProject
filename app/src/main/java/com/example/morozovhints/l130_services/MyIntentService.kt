package com.example.morozovhints.l130_services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.morozovhints.R


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
        const val FOREGROUND_LOG_TAG = "intent_service_tag"
        const val NAME = "intent_service"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java).apply {
            }
        }
    }
}