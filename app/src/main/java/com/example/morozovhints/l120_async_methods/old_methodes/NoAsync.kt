package com.example.morozovhints.l120_async_methods.old_methodes

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.morozovhints.databinding.ActivityMainAsyncBinding


/**
 * Без асинхронности.
 */
class NoAsync(private val viewBinding: ActivityMainAsyncBinding, val context: Context) {

    private fun loadDataByMainThread() {
        //Это синхронный код
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        val city = loadCityByMainThread()
        viewBinding.tvCitySet.text = city
        val weather = loadWeatherByMainThread(city)
        viewBinding.tvWeatherSet.text = weather
        viewBinding.progressBarOfDownloading.isVisible = false
        viewBinding.btnDownload.isEnabled = true
    }

    private fun loadCityByMainThread(): String {
        Thread.sleep(5000)
        return "Королев"
    }

    private fun loadWeatherByMainThread(city: String): String {
        Toast.makeText(context, "Загрузка погоды... $city", Toast.LENGTH_LONG).show()
        Thread.sleep(5000)
        return "Дождь"
    }
}