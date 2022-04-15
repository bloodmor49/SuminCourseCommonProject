package com.example.morozovhints.L03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.os.Handler
import com.example.morozovhints.R
import kotlinx.coroutines.Runnable

class L3MainTimerActivity : AppCompatActivity() {

    private var seconds:Int = 0
    private var isRunning:Boolean = false
    private var wasRunning: Boolean = false
    private lateinit var timerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        timerTextView = findViewById(R.id.TextViewTimer)
        runTimer()
    }
    //Сохранение данных при повороте экрана - происходит изменение конфигурации OnDestroied
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(outState) {
            putInt("seconds", seconds)
            putBoolean("isRunning", isRunning)
            putBoolean("wasRunning", wasRunning)
        }
    }
    //Пересоздается экран с перезаписью всех переменных
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        seconds = savedInstanceState.getInt("seconds")
        isRunning = savedInstanceState.getBoolean("isRunning")
        wasRunning = savedInstanceState.getBoolean("wasRunning")
    }

    /* Не нужно, заменяется onPause, onResume в соответствии с условиями задачи.
    override fun onStop() {
        super.onStop()
        wasRunning = isRunning
        isRunning = false
    }

    override fun onStart() {
        super.onStart()
        isRunning = wasRunning
    }
*/
    override fun onPause() {
        super.onPause()
        wasRunning = isRunning
        isRunning = false
    }
    override fun onResume() {
        super.onResume()
        isRunning = wasRunning
    }

    fun startTimer(view: View) {
        isRunning = true
    }
    fun stopTimer(view: View) {
        isRunning = false
    }
    fun resetTimer(view: View) {
        isRunning = false
        seconds = 0
    }

    private fun runTimer(){
        //Создаем объект класса Handler
        val timeHandler = Handler()
        //далее запускаем отсчет времени прямо сейчас, создавая анонимный класс Runnable
        timeHandler.post(object: Runnable {
            //стандартная функция запуска процесса run класса Runnable
            override fun run() {
                var hours = seconds / 3600
                var minutes = (seconds % 3600) / 60
                var secs = seconds % 60
                var time = String.format( "%d:%02d:%02d", hours, minutes, secs)
                timerTextView.text = time
                if (isRunning) seconds++

                //при выполнении задания ждем минуту и снова запускаем анонимный класс

                timeHandler.postDelayed(this,1000)
            }
        }
        )
    }
}

