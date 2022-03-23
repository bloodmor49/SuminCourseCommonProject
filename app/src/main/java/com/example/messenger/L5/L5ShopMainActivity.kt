package com.example.messenger.L5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.messenger.R

class L5ShopMainActivity : AppCompatActivity() {
    
    private lateinit var listViewCups : ListView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l5_shop_main)
        actionBar?.hide()
        listViewCups = findViewById(R.id.listViewCups)
        
        listViewCups.setOnItemClickListener { parent, view, position, id ->
           var toast = Toast.makeText(applicationContext, "Нажата $position", Toast.LENGTH_SHORT).show()

            when (position){
                0 -> startActivity(Intent(applicationContext, L5CupActivity::class.java))
                1 -> toast
                2 -> toast
                3 -> startActivity(Intent(applicationContext, L5addSeekBarActivity::class.java))
            }
        }
    }
}