package com.example.morozovhints.l120

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.morozovhints.R
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlin.concurrent.thread


//Напоминаемс. Основной поток отвечает за визуализацию - отображение кнопок, экранов и т.д.
//Если в основном потоке запустить загрузку данных либо усыпить его, то вся визуализация зависнет и
//всё крашнется.
class MainActivityAsync : AppCompatActivity() {

    //viewBinding элементов XML
    private val viewBinding by lazy {
        ActivityMainAsyncBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_async)
        viewBinding.btnDownload.setOnClickListener {
            //loadDataByMainThread() //не заработает - главный поток
            loadDataByCallback()
        }
    }

    private fun loadDataByCallback() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        //стартуем загрузку
        loadCityByCallback { city: String ->
            viewBinding.tvCitySet.text = city
            //по завершению снова начинаем загрузку
            loadWeatherByCallback(city) { weather: String ->
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }

        }

    }

    private fun loadCityByCallback(callback: (String) -> Unit) {
        //Создаем новый поток
        //Это асинхронный код.
        thread {
            Thread.sleep(5000)
            //так как это другой поток, то нельзя просто взять и вернуть строку
            //поэтому используем callback
            callback.invoke("Королев")
            //
        }

    }

    private fun loadWeatherByCallback(city: String, callback: (String) -> Unit) {
        thread {
            Toast.makeText(this, "Загрузка погоды...", Toast.LENGTH_LONG).show()
            Thread.sleep(5000)
            callback.invoke("Дождь")
        }
    }


    /////////////НЕ ЗАРАБОТАЕТ - ЗАГРУЗКА С ГЛАВНОГО ПОТОКА///////////////////////////

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
        Toast.makeText(this, "Загрузка погоды...", Toast.LENGTH_LONG).show()
        Thread.sleep(5000)
        return "Дождь"
    }

    //////////////////////////////////////////////////////////////////////////////////

}