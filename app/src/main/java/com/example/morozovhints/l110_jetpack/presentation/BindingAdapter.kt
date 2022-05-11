package com.example.morozovhints.l110_jetpack.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.morozovhints.l110_jetpack.domain.entity.GameResult
import com.example.morozovhints.R

//замена в XML андроид текст. Мы создаем отдельную функцию как TEXT, называем её как ниже
//а потом привязываем к ней наш текст + количество.
@BindingAdapter("requireAnswers")
fun bindRequireAnswers(textView: TextView, count: Int) {
    textView.text = String.format(textView.context.getString(R.string.required_score), count)
}

//в функциях первый параметр - это представление из XML. Например TextView.
//Второй параметр - это то, что мы хотим поместить в XML представление.
//Его же мы задаем в = -ве.
@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(textView.context.getString(R.string.score_answers), count)
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(textView.context.getString(R.string.required_percentage), percentage)
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult))
}

private fun getPercentOfRightAnswers(gameResult:GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner:Boolean){
    imageView.setImageResource(getSmileResId(winner))
}

fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.common_google_signin_btn_icon_light
    } else {
        R.drawable.common_google_signin_btn_icon_dark
    }
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean){
    textView.setTextColor(getColorByState(textView.context,enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean){
    val color = getColorByState(progressBar.context,enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}


fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.click(textView.text.toString().toInt() )
    }
}

interface OnOptionClickListener{
    fun click(option: Int)
}


