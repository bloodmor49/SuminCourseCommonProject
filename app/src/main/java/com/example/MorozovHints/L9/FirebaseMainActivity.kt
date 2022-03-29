package com.example.MorozovHints.L9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.MorozovHints.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_main)

        val db = Firebase.firestore
        //addDataToFireCloud(db)
        //readDataFromFireCloud(db)
        fireCloudListener(db)


    }

    private fun addDataToFireCloud(db: FirebaseFirestore) {
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("Firecloud", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firecloud", "Error adding document", e)
            }
    }

    private fun readDataFromFireCloud(db: FirebaseFirestore) {

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firecloud", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firecloud", "Error getting documents.", exception)
            }
    }

    private fun fireCloudListener(db: FirebaseFirestore) {
        db.collection("users")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("Firecloud", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    for (document in snapshot) {
                        Log.d("Firecloud", "${document.id} => ${document.data}")
                    }
                } else {
                    Log.d("Firecloud", "Current data: null")
                }
            }
    }
}

