package com.example.morozovhints.l111_fragments_training

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("L111DataIntToFormat")
fun bindL111DataIntToFormat(textView:TextView,i:Int){
    textView.text = "Значение Int L11DATA = $i"
}