package com.example.MorozovHints.L5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.MorozovHints.R

class L5CupDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l5_cup_description)
        actionBar?.hide()

        var title = intent.getStringExtra("title")
        var info = intent.getStringExtra("info")
        var imageResourcesId = intent.getIntExtra("imageResourcesId",-1)

        var textViewCupTitle = findViewById<TextView>(R.id.textViewCupTitle)
        var textViewCupInfo = findViewById<TextView>(R.id.textViewCupInfo)
        var imageViewCupType = findViewById<ImageView>(R.id.imageViewCupType)

        textViewCupTitle.text = title
        textViewCupInfo.text = info
        imageViewCupType.setImageResource(imageResourcesId)
    }
}