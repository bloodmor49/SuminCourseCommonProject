package com.example.messenger.L4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import com.example.messenger.R

class L4CreateOrderActivity : AppCompatActivity() {

    private lateinit var textViewHello :TextView
    private lateinit var textViewAdittion : TextView
    private lateinit var checkBoxMilk : CheckBox
    private lateinit var checkBoxSugar :CheckBox
    private lateinit var checkBoxLemon :CheckBox
    private lateinit var spinnerTea : Spinner
    private lateinit var spinnerCoffee :Spinner
    private lateinit var drink : String
    private lateinit var builderAdditions: StringBuilder
    private lateinit var name : String
    private lateinit var password : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)

        textViewHello = findViewById(R.id.textViewHello)
        textViewAdittion = findViewById(R.id.textViewAdittion)
        checkBoxMilk = findViewById(R.id.checkBoxMilk)
        checkBoxSugar = findViewById(R.id.checkBoxSugar)
        checkBoxLemon = findViewById(R.id.checkBoxLemon)
        spinnerTea = findViewById(R.id.spinnerTea)
        spinnerCoffee = findViewById(R.id.spinnerCoffee)

        drink = getString(R.string.TeaRadioButton)

        name = intent.getStringExtra("stringName").toString()
        password = intent.getStringExtra("stringPassword").toString()

        textViewHello.text = String.format(getString(R.string.ClientWelcomeText), name)
        textViewAdittion.text = String.format(getString(R.string.WhatToAddText),drink)

    }

    fun onClickChangeDrink(view: View) {

        var button = view as RadioButton
        var id = button.id
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.TeaRadioButton)
            spinnerTea.visibility = View.VISIBLE
            spinnerCoffee.visibility = View.INVISIBLE
            checkBoxLemon.visibility = View.VISIBLE
        }
        else if (id == R.id.radioButtonCoffee){
            drink = getString(R.string.CoffeeRadioButton)
            spinnerTea.visibility = View.INVISIBLE
            spinnerCoffee.visibility = View.VISIBLE
            checkBoxLemon.visibility = View.INVISIBLE
        }

        var additions = String.format(getString(R.string.WhatToAddText),drink)
        textViewAdittion.text = additions

    }



    fun sendOrder(view: View) {
        builderAdditions = StringBuilder("")
        if (checkBoxMilk.isChecked){
            builderAdditions.append(getString(R.string.MilkCheckBox)).append("\n")
        }
        if (checkBoxLemon.isChecked){
            builderAdditions.append(getString(R.string.LemonCheckBox)).append("\n")
        }
        if (checkBoxSugar.isChecked){
            builderAdditions.append(getString(R.string.SugarTextBox)).append("\n")
        }

        var optionOfDrink = ""
        optionOfDrink = if (drink == getString(R.string.TeaRadioButton))
            spinnerTea.selectedItem.toString()
        else spinnerCoffee.selectedItem.toString()

        var order = "Имя: $name\nПароль: $password\nНапиток: $drink\nВид: $optionOfDrink\n"
        var additions = if (builderAdditions.isNotEmpty()) "\nНеобходимые добавки: \n$builderAdditions"
        else ""

        var fullOrder = order + additions

        var intent = Intent(this, L4OrderDetailsActivity::class.java)
        intent.putExtra("stringFullOrder",fullOrder)
        startActivity(intent)
    }
}