package com.example.morozovhints.l120_async_methods.old_methodes

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlin.concurrent.thread

class Callbacks(private val viewBinding: ActivityMainAsyncBinding, val context: Context) {

    private fun loadDataByCallback() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        loadCityByCallback { city: String ->
            viewBinding.tvCitySet.text = city
            loadWeatherByCallback(city) { weather: String ->
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }
        }
    }

    private fun loadCityByCallback(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            callback.invoke("Королев")
        }
    }

    private fun loadWeatherByCallback(city: String, callback: (String) -> Unit) {
        thread {
            Toast.makeText(context, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            Thread.sleep(5000)
            callback.invoke("Дождь")
        }
    }
}