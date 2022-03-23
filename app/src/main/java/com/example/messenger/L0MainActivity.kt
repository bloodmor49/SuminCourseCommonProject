package com.example.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.messenger.L1.L1MainColorSpinnerActivity
import com.example.messenger.L2.L2MainCreateMessageActivity
import com.example.messenger.L3.L3MainTimerActivity
import com.example.messenger.L4.L4MainLoginActivity
import com.example.messenger.L5.L5ShopMainActivity
import com.example.messenger.L6.L6JSONProjectActivity
import com.example.messenger.L71.L7DataStoreMainActivity
import com.example.messenger.L72.ViewsMainActivity

class L0MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.show()

        var mainListView = findViewById<ListView>(R.id.mainListView)

        mainListView.setOnItemClickListener { parent, view, position, id ->

            when (position) {

                0 -> startActivity(Intent(this, L1MainColorSpinnerActivity::class.java))
                1 -> startActivity(Intent(this, L2MainCreateMessageActivity::class.java))
                2 -> startActivity(Intent(this, L3MainTimerActivity::class.java))
                3 -> startActivity(Intent(this, L4MainLoginActivity::class.java))
                4 -> startActivity(Intent(this, L5ShopMainActivity::class.java))
                5 -> startActivity(Intent(this, L6JSONProjectActivity::class.java))
                6 -> startActivity(Intent(this, L7DataStoreMainActivity::class.java))
                7 -> startActivity(Intent(this, ViewsMainActivity::class.java))
            }

        }
    }
}