package com.example.morozovhints.L02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.morozovhints.R

class L2ActivityRecievedMessage : AppCompatActivity() {

    private lateinit var  textview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recieved_message)
        textview = findViewById(R.id.textReceived)
        var tts = intent.getStringExtra("tts")
        textview.text = tts
    }
}