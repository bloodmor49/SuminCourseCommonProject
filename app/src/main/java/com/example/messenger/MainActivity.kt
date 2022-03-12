package com.example.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun lesson1intent(view: View) {
        intent = Intent(this,ColorSpinnerActivity::class.java)
        startActivity(intent)
    }

    fun lesson2intent(view: View) {
        intent = Intent(this,CreateMessageActivity::class.java)
        startActivity(intent)
    }

    fun lesson3intent(view: View) {
        intent = Intent(this,TimerActivity::class.java)
        startActivity(intent)
    }
}