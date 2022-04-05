package com.example.MorozovHints.L05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.MorozovHints.R

class L5CupActivity : AppCompatActivity() {

    private lateinit var listViewCupsType : ListView
    private lateinit var listOfCups : MutableList<L5cup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l5_cup)
        listViewCupsType = findViewById(R.id.listViewCupsType)
        actionBar?.hide()

        listOfCups = mutableListOf()
        listOfCups.add(L5cup(getString(R.string.titleCupGradient),getString(R.string.infoCupGradient),
            R.drawable.cup1))
        listOfCups.add(L5cup(getString(R.string.titleCupRed),getString(R.string.infoCupRed),
            R.drawable.cup2))
        listOfCups.add(L5cup(getString(R.string.titleCupBlack),getString(R.string.infoCupBlack),
            R.drawable.cup3))

        var listAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1, listOfCups)
        listViewCupsType.adapter = listAdapter
        
        listViewCupsType.setOnItemClickListener { parent, view, position, id ->
            var cup = listOfCups[position]
            var intent = Intent(applicationContext, L5CupDescriptionActivity::class.java)
            intent.putExtra("title",cup.title)
            intent.putExtra("info",cup.info)
            intent.putExtra("imageResourcesId",cup.imageResourcesId)
            startActivity(intent)

        }
    }
}