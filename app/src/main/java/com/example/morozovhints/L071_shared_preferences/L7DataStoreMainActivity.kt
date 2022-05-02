package com.example.morozovhints.L071_shared_preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.morozovhints.R
import java.util.*
import kotlin.random.Random

class L7DataStoreMainActivity : AppCompatActivity() {

    private lateinit var button1 : Button
    private lateinit var button2 : Button
    private lateinit var button3 : Button
    private lateinit var button4 : Button
    lateinit var textViewMathQuestion : TextView
    lateinit var textViewTimer : TextView
    lateinit var textViewScore : TextView

    private var rightAnswer = 0
    private var listOfButtons = mutableListOf<Button>()
    private var rightAnswerPosition = 0

    var rightAnswers = 0
    var allQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l7_data_store_main)

        textViewTimer = findViewById(R.id.textViewTimer)
        textViewMathQuestion = findViewById(R.id.textViewMathQuestion)
        textViewScore = findViewById(R.id.textViewScore)

        button1 = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        listOfButtons.add(button1)
        listOfButtons.add(button2)
        listOfButtons.add(button3)
        listOfButtons.add(button4)

        nextGame()

            /*
            var preferences = getDefaultSharedPreferences(this)
            preferences.edit().putInt("test",5).apply()
            var test = preferences.getInt("test",0)
            Toast.makeText(this," $test",Toast.LENGTH_LONG).show()
            */

        var timer = (object: CountDownTimer(15000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished/1000%60).toInt()
                var minutes = (millisUntilFinished/60000).toInt()
                textViewTimer.text = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds)

            }

            override fun onFinish() {
                Toast.makeText(this@L7DataStoreMainActivity,
                    "Игра окончена",
                    Toast.LENGTH_LONG)
                    .show()

                var resultIntent = Intent(this@L7DataStoreMainActivity,
                    L7GameResultActivity::class.java)
                resultIntent.putExtra("score",rightAnswers)
                startActivity(resultIntent)

            }
        })
        timer.start()

    }

    private fun generateMathQuestion(){

        var a = Random.nextInt(0,100)
        var b = Random.nextInt(0,100)
        rightAnswer = a + b
        textViewMathQuestion.text = "$a + $b = ?"
        rightAnswerPosition = Random.nextInt(0,3)
        }

    private fun nextGame() {
        textViewScore.text = "$rightAnswers / $allQuestions"
        generateMathQuestion()
        for (i in listOfButtons.indices) {
            if (i == rightAnswerPosition) listOfButtons[i].text = rightAnswer.toString()
            else listOfButtons[i].text = Random.nextInt(0, 200).toString()
        }
    }

    fun buttonClick(view: View) {
        var position = listOfButtons.indexOf(button1)
        answerCheck(position)
    }
    fun button2Click(view: View) {
        var position = listOfButtons.indexOf(button2)
        answerCheck(position)
    }
    fun button3Click(view: View) {
        var position = listOfButtons.indexOf(button3)
        answerCheck(position)
    }
    fun button4Click(view: View) {
        var position = listOfButtons.indexOf(button4)
        answerCheck(position)
    }

    private fun answerCheck(position: Int){
        if (position == rightAnswerPosition) {
            Toast.makeText(this@L7DataStoreMainActivity,
                "Ответ правильный",
                Toast.LENGTH_LONG)
                .show()
            scoreCheck(true)
        }else
            {
            Toast.makeText(this@L7DataStoreMainActivity,
                "Ответ неправильный",
                Toast.LENGTH_LONG)
                .show()
            scoreCheck(false)
            }
        nextGame()
    }
    private fun scoreCheck(answerIsRight:Boolean){
        if (answerIsRight) rightAnswers++
        allQuestions++
    }

}

