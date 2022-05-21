package com.example.morozovhints.l130_services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.morozovhints.R

class MyAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notificationManager =
                getSystemService(
                    it,
                    NotificationManager::class.java
                ) as NotificationManager

            createNotificationChannel(notificationManager)

            val notification = NotificationCompat.Builder(it, CHANNEL_ID)
                    .setContentTitle("MyAlarmReceiver ")
                    .setContentText("Текст уведомления MyAlarmReceiver")
                    .setSmallIcon(R.drawable.logo_okabe)

            notificationManager.notify(NOTIFICATION_ID,notification.build())
            }
        }

    private fun createNotificationChannel(notificationManager:NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                MyForeGroundService.CHANNEL_ID,
                MyForeGroundService.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


    companion object {

        const val CHANNEL_ID = "channel_id"

        // появляется при долгом нажатии на уведомление. Можно отключать каналы.
        // в мессенджерах можно создать кучу каналов для разных штуках.
        const val CHANNEL_NAME = "channel_name"
        const val NOTIFICATION_ID = 1
        const val FOREGROUND_LOG_TAG = "foreground_service_tag"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyAlarmReceiver::class.java).apply {
            }
        }
    }
}