package com.example.morozovhints.l120_async_methods

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * MainAct - корутины.
 */
class MainActivityAsync : AppCompatActivity() {

    private val viewBinding: ActivityMainAsyncBinding by lazy {
        ActivityMainAsyncBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

//    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.btnDownload.setOnClickListener {
            viewBinding.progressBarOfDownloading.isVisible = true
            viewBinding.btnDownload.isEnabled = false
            val jobCity = lifecycleScope.launch {
                val city = loadCity()
                viewBinding.tvCitySet.text = city
            }
            val defferedWeather: Deferred<String> =lifecycleScope.async {
                val weather = loadWeather()
                viewBinding.tvWeatherSet.text = weather
                weather
            }
            lifecycleScope.launch {
                jobCity.join()
                val weather = defferedWeather.await()
                Toast.makeText(
                    this@MainActivityAsync,
                    "Погода...$weather",
                    Toast.LENGTH_LONG
                ).show()

                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }

        }

    }
    private suspend fun loadData() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        val city = loadCity()

        viewBinding.tvCitySet.text = city
        val weather = loadWeather()

        viewBinding.tvWeatherSet.text = weather
        viewBinding.progressBarOfDownloading.isVisible = false
        viewBinding.btnDownload.isEnabled = true
    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Королев"
    }

    private suspend fun loadWeather(): String {
        delay(5000)
        return "Дождь"
    }
}