package com.example.morozovhints.l130

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.PersistableBundle
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
 * При работе с апи > 26:
 * 
 * Если запустить несколько сервисов при помощи метода Shcedule, то работать будет только последний.
 * Метод enqueue же запустит последний прерванный сервис.
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            coroutineScope.launch {
//                 1) Достаем сервис первый из очереди.
                var workItem = params?.dequeueWork()
//                 2) Выполняем код до тех пор, пока в очереди есть ещё объекты.
                while (workItem != null) {
//                  3) Получаем значение page из намерения.
                    val page = workItem.intent.getIntExtra(PAGE, 0)
                    for (i in 0..100) {
                        delay(1000)
                        log("Timer: $i Page: $page")
                    }
                    //4) Конкретно один серви из очереди завершил работу. (Не все сервисы)
                    params?.completeWork(workItem)
                    //5) Достаем новый объект сервиса из очереди.
                    workItem = params?.dequeueWork()
                    //6) Завершаем работу, когда больше нет сервисов в очереди.
                    jobFinished(params, false)
                }
            }

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

    companion object {
        const val JOB_ID = 10
        const val PAGE = "page"

        //Bundle объект ключ - значение. Для schedule, где прошлые сервисы удаляются.
        fun newBundle(pageNum: Int): PersistableBundle {
            return PersistableBundle().apply {
                putInt(PAGE, pageNum)
            }
        }

        //Для enqueue, где прошлые сервисы удаляются.
        fun newIntent(pageNum: Int): Intent {
            return Intent().apply {
                putExtra(PAGE, pageNum)
            }
        }
    }
}
