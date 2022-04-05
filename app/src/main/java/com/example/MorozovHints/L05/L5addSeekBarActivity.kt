package com.example.MorozovHints.L05

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.example.MorozovHints.R

class L5addSeekBarActivity : AppCompatActivity() {

    private lateinit var seekBarMulTable : SeekBar
    private lateinit var listViewMulTable : ListView
    private var minV = 1
    private var maxV = 5
    private var count = 6

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l5add_seek_bar)

        seekBarMulTable = findViewById(R.id.seekBarMulTable)
        listViewMulTable = findViewById(R.id.listViewMulTable)
        seekBarMulTable.max = maxV
        seekBarMulTable.min = minV
        var listOfNumbers = mutableListOf<Int>(1,2,3)
        var stringAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,listOfNumbers)
        listViewMulTable.adapter = stringAdapter

        seekBarMulTable.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                listOfNumbers.clear()
                for (i in minV until count) listOfNumbers.add(progress * i)
                stringAdapter.notifyDataSetChanged()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        seekBarMulTable.progress = 2
    }
}