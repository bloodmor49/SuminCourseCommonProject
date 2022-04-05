package com.example.MorozovHints.L04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.MorozovHints.R

class L4OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        var textViewOrderFull = findViewById<TextView>(R.id.textViewOrderFull)

        var intentMy = intent
        if (intentMy.hasExtra("stringFullOrder")) {
            var order = intentMy.getStringExtra("stringFullOrder")
            textViewOrderFull.text = order
        } else {
            var backIntent = Intent(this, L4CreateOrderActivity::class.java)
            startActivity(backIntent)
        }
    }
}