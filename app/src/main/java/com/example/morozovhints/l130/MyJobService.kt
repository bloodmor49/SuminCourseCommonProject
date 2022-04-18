package com.example.morozovhints.l130

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*


///////////////////////////////////СОБЕСЕДОВАНИЕ//////////////////////////////
//TODO() Сервисы: Какие значения может возвращаться метод onStartCommand.

/**
 * JobService - наследник JobService. Переопределяются 2 метода.
 * Не нужен метод onStartCommand - его операции проводятся в onStartJob.
 *      onStartJob - вып-ся на главном потоке.
 * Код в нем может быть синхронным. Возвращает: выполняется ли все ещё работа или нет.
 * return true - сервис все ещё выполняется, мы его выключим сами.
 * return false - сервис не выполняется, сам завершит работу.
 *      onStopJob - когда сервис остановлен. Например когда wifi отключился.
 * Если мы сами остановили сервис, то onStopJob не вызовется, так как им рулит система.
 *
 */
class MyJobService : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    //Умирает
    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 0..100) {
                delay(1000)
                log("Timer: $i")
            }
            // что делать когда закончится работа? jobfinished - параметры, нужно ли запланировать
            // выполнение сервиса заного в определенное время?
            jobFinished(params,true)
        }
        return true
    }

    //когда
    override fun onStopJob(params: JobParameters?): Boolean {
        log("onStopJob")
        return true
    }

    private fun log(message: String) {
        Log.d("service_tag", "My service: $message")
    }
}