package com.example.morozovhints.l150_broadcast_receiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.concurrent.thread

class MyService : Service() {

    private val localBroadcastManager by lazy{
        LocalBroadcastManager.getInstance(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            for (i in 1..10) {
                Thread.sleep(1000)
                Intent("loaded").apply {
                    putExtra("percent",i*10)
                        localBroadcastManager.sendBroadcast(this)
                }

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}