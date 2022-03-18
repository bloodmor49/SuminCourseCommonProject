package com.example.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class L0MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.show()

        var mainListView = findViewById<ListView>(R.id.mainListView)

        mainListView.setOnItemClickListener { parent, view, position, id ->

            when (position) {

                0 -> startActivity(Intent(this,L1MainColorSpinnerActivity::class.java))
                1 -> startActivity(Intent(this,L2MainCreateMessageActivity::class.java))
                2 -> startActivity(Intent(this,L3MainTimerActivity::class.java))
                3 -> startActivity(Intent(this,L4MainLoginActivity::class.java))
                4 -> startActivity(Intent(this,L5ShopMainActivity::class.java))
                5 -> startActivity(Intent(this,L6JSONProjectActivity::class.java))

            }

        }
    }
}