package com.example.MorozovHints.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.MorozovHints.R
import com.google.firebase.firestore.FirebaseFirestore

class TestZoneActivity : AppCompatActivity() {
    lateinit var dbf: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_zone)
    }


    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}