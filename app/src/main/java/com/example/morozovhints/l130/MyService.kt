package com.example.morozovhints.l130

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*


///////////////////////////////////СОБЕСЕДОВАНИЕ//////////////////////////////
//TODO() Сервисы: Какие значения может возвращаться метод onStartCommand.
//1) return START_STICKY - если система убьет сервис, то он будет пересоздан, но интент будет null
//2) return START_NOT_STICKY - если система убьюет сервис, то он НЕ будет пересоздан.
//3) return START_REDELIVER_INTENT - как START_STICKY, но интент БУДЕт доставлен в онстарткомманд.
//
//TODO() Сервисы: Начиная с какой версии андроид начались проблемы с сервисами? И что изменилось?
//
// Многие приложения ДО Версии апи 26 не нужно было уведомлять пользователя о работе сервиса.
// Однако это могло приводить к тому, что программа выполняла в сервисе в фоне
// непонятно какие задачи, незаметно от пользователя.
// После 26 версии необходимо уведомлять пользователя, иначе после onDestroy сервис не
// будет перезапущен. Для этог используется ForeGround Service (несмахиваемое уведомление).
//

/**
 * Сервис - существует в фоновом потоке приложения. Полезен для музыкальных приложений, например.
 * Наследуется от Service. Сервисы выполняются в фоновом потоке.
 * !!!!!!Однако код внутри onStartCommand реализуется в главном потоке.!!!!!!!!
 * Сервис должен быть зарегистрирован в манифесте.
 */
class MyService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    //Создается
    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    //Вся работа сервиса. Сюда прилетает Intent.
    //работает в main потоке
    //возвращает Int - при смерти сервиса и перезапуске тот пересоздается, а все переменные
    //intent обнуляются. Т.е. таймер, например, начинается с 0.


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        val start = intent?.getIntExtra(EXTRA_START, 0) ?: 0
        coroutineScope.launch {
            for (i in start..start + 100) {
                delay(1000)
                log("Timer: $i")
            }
            stopSelf()
        }
        return START_STICKY
//        return super.onStartCommand(intent, flags, startId)
    }

    //Умирает
    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun log(message: String) {
        Log.d("service_tag", "My service: $message")
    }

    //вызов сервисов идет через намерения, как и новых активити.
    companion object {
        private const val EXTRA_START = "start"
        fun newIntent(context: Context, start: Int): Intent {
            return Intent(context, MyService::class.java).apply {
                putExtra(EXTRA_START, start)
            }
        }
    }
}