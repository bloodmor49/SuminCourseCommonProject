package com.example.morozovhints.l130

import android.content.Context
import android.util.Log
import androidx.work.*


/**
 * WorkManager - сервис Worker.
 * Единственный метод - doWork. Он Выполняется в другом потоке, поэтому не нужно использовать
 * корутины.
 */
class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {

    //Здесь вся работа
    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0) ?: 0
        for (i in 1..10) {
            Thread.sleep(1000)
            log("Timer: $i Page: $page")
        }
        // Возврат одного из трех результатов.
        // 1. Success - все ок и сервис завершил работу.
        // 2. Failure - завершился с ошибкой и не будет перезапущен.
        // 3. Retry - завершился с ошибкой и будет перезапущен.
        return Result.success()
    }

    private fun log(message: String) {
        Log.d(WORKER_LOG_TAG, "My service: $message")
    }

    companion object {
        const val WORKER_LOG_TAG = "worker"
        const val PAGE = "page"
        const val WORK_NAME = "work name"

        //создаем реквест
        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>()
                //создаем объект data
                .setInputData(workDataOf(PAGE to page))
                //устанавливаем ограничения ( работает во время зарядки)
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints(): Constraints {
            return Constraints.Builder()
                //работает от зарядки
                .setRequiresDeviceIdle(true)
                .build()
        }
    }
}