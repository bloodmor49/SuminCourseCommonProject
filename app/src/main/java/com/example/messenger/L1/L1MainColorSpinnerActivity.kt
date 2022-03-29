package com.example.messenger.L1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import com.example.messenger.LessonInfo
import com.example.messenger.R

class L1MainColorSpinnerActivity : AppCompatActivity() {

    lateinit var textColor : TextView
    lateinit var spinnerColor : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_spinner)
        textColor = findViewById(R.id.textViewColorDescr)
        spinnerColor = findViewById(R.id.spinnerColorDescr)
    }

    fun colorDescription(view: View) {
        var colorDescription = getDescriptionFromPosition(spinnerColor.selectedItemPosition)
        textColor.text = colorDescription
    }

    private fun getDescriptionFromPosition(position: Int) : String
    = resources.getStringArray(R.array.textViewColorChange)[position]

}