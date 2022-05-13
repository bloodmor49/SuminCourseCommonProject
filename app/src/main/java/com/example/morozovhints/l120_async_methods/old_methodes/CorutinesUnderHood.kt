package com.example.morozovhints.l120_async_methods.old_methodes

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlin.concurrent.thread

/**
 * Под капотом работы корутин.
 */
class CorutinesUnderHood(private val viewBinding: ActivityMainAsyncBinding, val context: Context) {

    private fun loadDataWithoutCourutines(step: Int = 0, obj: Any? = null) {
        //по шагам блоков кода. Похожее поведение, как корутины, но подробно как это работает.
        //Используется state машина с конструкцией when. В функции delay вообще используется postdelay.
        when (step) {
            0 -> {
                viewBinding.progressBarOfDownloading.isVisible = true
                viewBinding.btnDownload.isEnabled = false
                loadCityWithoutCourutines {
                    loadDataWithoutCourutines(step = 1, it)
                }
            }
            1 -> {
                val city = obj as String
                viewBinding.tvCitySet.text = city
                loadWeatherWithoutCourutines(city) {
                    loadDataWithoutCourutines(step = 2, it)
                }

            }
            2 -> {
                val weather = obj as String
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }
        }

    }

    private fun loadCityWithoutCourutines(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
//            runOnUiThread {
            Handler(Looper.getMainLooper()).post {
                callback.invoke("Королев")
            }
        }
    }

    private fun loadWeatherWithoutCourutines(city: String, callback: (String) -> Unit) {
        thread {
//            runOnUiThread {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            }
            Thread.sleep(5000)
//            runOnUiThread {
            Handler(Looper.getMainLooper()).post {
            }
            callback.invoke("Дождь")
        }
    }
}
