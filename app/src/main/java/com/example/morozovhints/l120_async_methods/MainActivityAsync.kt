package com.example.morozovhints.l120_async_methods

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 * MainAct - корутины.
 */
class MainActivityAsync : AppCompatActivity() {

    private val viewBinding: ActivityMainAsyncBinding by lazy {
        ActivityMainAsyncBinding.inflate(layoutInflater)
    }

//    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.btnDownload.setOnClickListener {
            lifecycleScope.launch {
                loadDataByCourutines()
            }

//            или
//            scope.launch {
//                loadDataByCourutines()
//            }
        }

    }
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
        Toast.makeText(this, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
        delay(5000)
        return "Дождь"
    }
}