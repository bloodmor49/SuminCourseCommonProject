package com.example.morozovhints.l120_async_methods.old_methodes

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlin.concurrent.thread

class HandlerLooper(private val viewBinding: ActivityMainAsyncBinding, val context: Context) {

    private val handler = Handler()

//    Как обрабатывать сообщения?
//    private val handler = object: Handler() {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            println("HANDLE_MSG $msg")
//        }
//    }

//      отправка сообщений в хендлер. Редко случается.
//        handler.sendMessage(Message.obtain(handler, 0, 17))
//

    /////////////Handler(Looper): Актуальное использование хендлера - нет жизн. цикла/
    private fun loadDataByHandlerNew() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        loadCityByHandlerNew { city: String ->
            viewBinding.tvCitySet.text = city
            loadWeatherByHandlerNew(city) { weather: String ->
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }
        }
    }

    private fun loadCityByHandlerNew(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)

            Handler(Looper.getMainLooper()).post {
                callback.invoke("Королев")
            }

//            вместо вывода выше можео использовать функцию снизу
//            runOnUiThread {
//                callback.invoke("Королев")
//            }
        }
    }

    private fun loadWeatherByHandlerNew(city: String, callback: (String) -> Unit) {
        thread {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            }
            Thread.sleep(5000)
            Handler(Looper.getMainLooper()).post {
                callback.invoke("Дождь")
            }
        }
    }

    //Устаревшее использование хендлера.

    private fun loadDataByHandler() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        loadCityByHandler { city: String ->
            viewBinding.tvCitySet.text = city
            loadWeatherByHandler(city) { weather: String ->
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }
        }
    }

    private fun loadCityByHandler(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            handler.post {
                callback.invoke("Королев")
            }
        }
    }

    private fun loadWeatherByHandler(city: String, callback: (String) -> Unit) {
        thread {
            handler.post {
                Toast.makeText(context, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            }
            Thread.sleep(5000)
            handler.post {
                callback.invoke("Дождь")
            }
        }
    }
}