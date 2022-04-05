package com.example.MorozovHints.L091

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.MorozovHints.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddNewUserActivity : AppCompatActivity() {

    private lateinit var editTextTextName: EditText
    private lateinit var editTextTextSurname: EditText
    private lateinit var editTextTextAge: EditText
    private lateinit var editTextTextGender: EditText
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTextName = findViewById(R.id.editTextTextName)
        editTextTextSurname = findViewById(R.id.editTextTextSurname)
        editTextTextAge = findViewById(R.id.editTextTextAge)
        editTextTextGender = findViewById(R.id.editTextTextGender)

        db = Firebase.firestore


    }

    fun downloadToFireCloud(view: View) {
        val name = editTextTextName.text.toString().trim()
        val surname = editTextTextSurname.text.toString().trim()
        val gender = editTextTextGender.text.toString().trim()
        var age: Int? = null

        try {
            age = editTextTextAge.text.toString().trim().toInt()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        if (age != null) {
            if (name.isEmpty() ||
                surname.isEmpty() ||
                gender.isEmpty() ||
                age < 0
            ) {
                Toast.makeText(this, "Неправильно заполнены поля", Toast.LENGTH_LONG).show()
            } else {
                val user = L9User(name, surname, age, gender)
                // Add a new document with a generated ID
                db.collection("usersHW")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d("Firecloud", "DocumentSnapshot added with ID: ${documentReference.id}")
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firecloud", "Error adding document", e)
                    }

            }
        }
    }
}