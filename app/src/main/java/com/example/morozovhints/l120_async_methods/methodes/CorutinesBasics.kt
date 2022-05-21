package com.example.morozovhints.l120_async_methods.methodes

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlinx.coroutines.delay

class CorutinesBasics(private val viewBinding: ActivityMainAsyncBinding, val context: Context) {

    /////////////Courutines: Корутины - потоки для котлина.//////////////////////////
    private suspend fun loadDataByCourutines() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        val city = loadCityByCourutines()

        viewBinding.tvCitySet.text = city
        val weather = loadWeatherByCourutines(city)

        viewBinding.tvWeatherSet.text = weather
        viewBinding.progressBarOfDownloading.isVisible = false
        viewBinding.btnDownload.isEnabled = true
    }

    private suspend fun loadCityByCourutines(): String {
        delay(5000)
        return "Королев"
    }

    private suspend fun loadWeatherByCourutines(city: String): String {
        Toast.makeText(context, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
        delay(5000)
        return "Дождь"
    }
}