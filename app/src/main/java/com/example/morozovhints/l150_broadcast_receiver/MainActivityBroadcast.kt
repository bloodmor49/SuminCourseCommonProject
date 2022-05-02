package com.example.morozovhints.l150_broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.morozovhints.R
import com.example.morozovhints.l150_broadcast_receiver.MyReceiver.Companion.ACTION_CLICKED

class MainActivityBroadcast : AppCompatActivity() {

    private val localBroadcastManager by lazy{
        LocalBroadcastManager.getInstance(this)
    }

    private val receiver = MyReceiver()

    private lateinit var progressBar : ProgressBar

    private val progressBarReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "loaded"){
                val percent = intent.getIntExtra("percent",0)
                progressBar.progress = percent
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_broadcast)
        findViewById<Button>(R.id.buttonReceiver).setOnClickListener {
            val intent = Intent(MyReceiver.ACTION_CLICKED)
            localBroadcastManager.sendBroadcast(intent)
        }

        progressBar = findViewById(R.id.progressBarReceiver)

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(MyReceiver.ACTION_CLICKED)
            addAction("loaded")
        }

        //регистрация ресиверов

        localBroadcastManager.registerReceiver(receiver,intentFilter)

        localBroadcastManager.registerReceiver(progressBarReceiver,intentFilter)


        Intent(this,MyService::class.java).apply {
            startService(this)
        }
    }

    override fun onDestroy() {
        localBroadcastManager.unregisterReceiver(receiver)
        super.onDestroy()
    }
}