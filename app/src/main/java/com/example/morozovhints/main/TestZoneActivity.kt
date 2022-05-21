package com.example.morozovhints.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.morozovhints.R
import com.google.firebase.firestore.FirebaseFirestore

class TestZoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_zone)
    }


    override fun onDestroy() {
        super.onDestroy()
        finish()
    }



}

class SomeClass(var a: Int = 1) {

        fun funClass() {}
}