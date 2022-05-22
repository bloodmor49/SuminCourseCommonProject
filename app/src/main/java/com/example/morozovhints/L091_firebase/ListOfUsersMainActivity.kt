package com.example.morozovhints.L091_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListOfUsersMainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var floatingActionButton: FloatingActionButton
    private var db : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_main)

        recyclerView = findViewById(R.id.recyclerViewUsers)
        floatingActionButton = findViewById(R.id.floatingActionButtonAddUsers)
        db = Firebase.firestore

        usersAdapter = UsersAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = usersAdapter

        testRealtimeFirestore()
    }

    private fun testRealtimeFirestore() {
        val database = Firebase.database
        val preference = database.reference.child("message")
        preference.setValue("NewMessage")
    }

    fun onClickAddUser(view: View) {
        startActivity(Intent(this,AddNewUserActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        db?.collection("usersHW")
            ?.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("Firecloud", "Listen failed.", e)
                    return@addSnapshotListener
                }
                    if (snapshot != null) {
                        var listOfUsers: MutableList<L9User> = snapshot.toObjects(L9User::class.java)
                        usersAdapter.listOfUsers = listOfUsers
                } else {
                    Log.d("Firecloud", "Current data: null")
                }
            }
    }
}

