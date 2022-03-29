package com.example.MorozovHints.L71

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.MorozovHints.R

class L7GameResultActivity : AppCompatActivity() {

    lateinit var textViewResult : TextView

    private lateinit var buttonNewGameStart : Button
    private lateinit var buttonResetHighScore : Button

    var rightAnswers = 0
    var bestResult = 0
    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l7_game_result)

        buttonNewGameStart = findViewById(R.id.buttonNewGameStart)
        buttonResetHighScore = findViewById(R.id.buttonResetHighScore)
        textViewResult = findViewById(R.id.textViewResult)

        preferences = getDefaultSharedPreferences(applicationContext)

        getResult()
    }

    fun buttonResetHighScore(view: View) {
        preferences.edit().putInt("bestResult",0).apply()
        rightAnswers = 0
        bestResult = 0
        textViewResult.text =
            "Результат игры\nПравильных ответов: $rightAnswers\nЛучший результат - $bestResult"
    }
    fun buttonNewGameStart(view: View) {
    startActivity(Intent(this, L7DataStoreMainActivity::class.java))
    }

    private fun getResult(){
        rightAnswers = intent.getIntExtra("score",0)
        bestResult = preferences.getInt("bestResult",0)
        if (bestResult <= rightAnswers) {
            preferences.edit().putInt("bestResult",rightAnswers).apply()
            bestResult = rightAnswers
        }
        textViewResult.text =
            "Результат игры\n Правильных ответов: $rightAnswers\nЛучший результат - $bestResult"
    }
}