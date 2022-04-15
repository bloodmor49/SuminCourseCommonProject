package com.example.morozovhints.L091

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListOfUsersPresenter {

    val db = Firebase.firestore

    fun addDataToFireCloud() {
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

    fun readDataFromFireCloud() {

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

    fun fireCloudListener() {
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