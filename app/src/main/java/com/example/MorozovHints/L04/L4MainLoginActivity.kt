package com.example.MorozovHints.L04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.MorozovHints.R

class L4MainLoginActivity : AppCompatActivity() {

    private lateinit var editTextName:EditText
    private lateinit var editTextPassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTextName = findViewById(R.id.editTextTextPersonName)
        editTextPassword = findViewById(R.id.editTextTextPassword)
    }

    fun onClickCreateOrder(view: View) {
        var stringName = editTextName.text.toString().trim()
        var stringPassword = editTextPassword.text.toString().trim()

        if (stringName.isNotEmpty() && stringPassword.isNotEmpty()){
            var intent = Intent(this, L4CreateOrderActivity::class.java)
            intent.putExtra("stringName",stringName)
            intent.putExtra("stringPassword",stringPassword)
            startActivity(intent)
        }
        else Toast.makeText(this, R.string.toast_not_filled, Toast.LENGTH_SHORT).show()
    }
}