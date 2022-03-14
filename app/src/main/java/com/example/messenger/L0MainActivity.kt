package com.example.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class L0MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun lesson1intent(view: View) {
        intent = Intent(this,L1MainColorSpinnerActivity::class.java)
        startActivity(intent)
    }
    fun lesson2intent(view: View) {
        intent = Intent(this,L2MainCreateMessageActivity::class.java)
        startActivity(intent)
    }
    fun lesson3intent(view: View) {
        intent = Intent(this,L3MainTimerActivity::class.java)
        startActivity(intent)
    }
    fun lesson4intent(view: View) {
        intent = Intent(this,L4MainLoginActivity::class.java)
        startActivity(intent)
    }
    fun lesson5intent(view: View) {
        intent = Intent(this,L5ShopMainActivity::class.java)
        startActivity(intent)
    }

}