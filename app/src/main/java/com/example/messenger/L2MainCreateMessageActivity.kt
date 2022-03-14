package com.example.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class L2MainCreateMessageActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)
        editText = findViewById(R.id.TextToSend)
    }

    fun sendMessage(view: View) {
        var tts = editText.text.toString()
        var sendIntent = Intent(this,L2ActivityRecievedMessage::class.java)
        sendIntent.putExtra("tts",tts)
        startActivity(sendIntent)

    }

    fun sendMessageActivity(view: View) {
        var tts = editText.text.toString()
        var sendIntentAction = Intent(Intent.ACTION_SEND)
        sendIntentAction.type = "text/plain"
        sendIntentAction.putExtra(Intent.EXTRA_TEXT,tts)
        startActivity(sendIntentAction)

    }
}