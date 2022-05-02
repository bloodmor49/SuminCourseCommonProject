package com.example.morozovhints.l150_broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * BroadCast reciever - один из четырех компонентов андроид.
 * Они нужны тогда, когда происходят события типа разрядки батареи, звонка и тд.
 * В каждый из этих моментов андроид отправляет сообщения.
 * Если нам нужно на них реагировать, то создается ресивер - получатель этих сообщений.
 * Мы можем подписаться на них и реагировать.
 */
class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //все сообщения подобного рода - это действия, которые мы получаем из намерений.
        val action = intent?.action
        when (action) {
            "loaded" -> {
                val percent = intent.getIntExtra("percent",0)
                Toast.makeText(context, "$percent %", Toast.LENGTH_LONG).show()
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                Toast.makeText(context, "ACTION_AIRPLANE_MODE_CHANGED", Toast.LENGTH_LONG).show()
            }

            Intent.ACTION_BATTERY_LOW -> {
                val state = intent.getBooleanExtra("state",false)
                Toast.makeText(context, "ACTION_BATTERY_LOW state $state", Toast.LENGTH_LONG).show()
            }

            ACTION_CLICKED -> {
                Toast.makeText(context, "ACTION_CLICKED", Toast.LENGTH_LONG).show()
            }
        }


    }

    companion object {
        const val ACTION_CLICKED = "clicked"
    }
}