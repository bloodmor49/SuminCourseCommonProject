package com.example.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import java.util.*

class TimerActivity : AppCompatActivity() {

    private var seconds = 0
    private var isRunning = false
    private var wasRunning = false
    private lateinit var textViewTimer : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        textViewTimer = findViewById(R.id.TextViewTimer)

        runTimer()
    }

    //сохранение и переинициализация данных при уничтожении активити (например - поворот экрана)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(outState) {
            putInt("seconds",seconds)
            putBoolean("isRunning",isRunning)
            putBoolean("isRunning",wasRunning)
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        seconds = savedInstanceState.getInt("seconds")
        isRunning = savedInstanceState.getBoolean("isRunning")
        wasRunning = savedInstanceState.getBoolean("wasRunning")
    }

    //работа при сбитии фокуса
    override fun onPause() {
        super.onPause()
        wasRunning = isRunning
        isRunning = false
    }
    override fun onResume() {
        super.onResume()
        isRunning = wasRunning
    }

    //кнопки
    fun startTimer(view: View) {
        isRunning = true
    }
    fun stopTimer(view: View) {
        wasRunning = isRunning
        isRunning = false
    }
    fun resetTimer(view: View) {
        isRunning = false
        textViewTimer.text = getString(R.string.timerInit)
    }

    //Handler - фоновый поток
    private fun runTimer(){
        var timerHandler = Handler()
        timerHandler.post(object :Runnable {
            override fun run() {
                var hours = seconds / 3600
                var minutes = (seconds % 3600) / 60
                var secs = seconds % 60
                var time = String.format( "%d:%02d:%02d", hours, minutes, secs)
                textViewTimer.text = time
                if (isRunning) seconds++

                timerHandler.postDelayed(this,1000)
            }
        })
    }
}





