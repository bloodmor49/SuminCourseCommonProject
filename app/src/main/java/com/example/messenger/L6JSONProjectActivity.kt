package com.example.messenger

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

class L6JSONProjectActivity : AppCompatActivity() {

    private var yandexURL = "https://yandex.ru/"
    private var imageURL =
        "https://img1.fonwall.ru/o/st/abstraction-long-exposure-colorful-neon.jpeg?route=mid&h=750"
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l6_jsonproject)
        Log.i("Hello", yandexURL) //вбиваем в лог текст с тегом hello

        var task = DownloadTask()
        @Suppress("DEPRECATION")
        try {
            var result = task.execute(yandexURL).get()
            Log.i("URL", result)
        } catch (e: Exception) {
            when {
                (e is ExecutionException) || (e is InterruptedException) -> e.printStackTrace()
            }
        }

        imageView = findViewById(R.id.imageView)


    }

    @Suppress("DEPRECATION")
    private class LogTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            params[0]?.let { Log.i("URL", it) }
            return "Готово"
        }
    }

    @Suppress("DEPRECATION")
    private class DownloadTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            var result = StringBuilder()
            var url: URL? = null

            try {
                //Создаем URL из строки
                url = URL(params[0])

                //Открываем URL соединение (как открыть сайт в браузере)
                var urlConnection = url.openConnection()

                //Создаем поток ввода для чтения данных
                var inPUT = urlConnection.inputStream

                //Создаем считывальщик вводных данных (он может их читать только по 1 символу)
                var reader = InputStreamReader(inPUT)

                //Создаем ридер для чтения всех строки
                var bufferedReader = BufferedReader(reader)

                //Начинаем процесс чтения данных - считываем 1 строку и заносим в line
                var line = bufferedReader.readLine()

                //повторяем пока строки не будут посчтитаны
                while (line != null) {
                    result.append(line)
                    line = bufferedReader.readLine()
                }
            } catch (e: Exception) {
                when (e) {
                    is MalformedURLException -> e.printStackTrace()
                    is IOException -> e.printStackTrace()
                }
            } finally {
                (null as HttpURLConnection?)?.disconnect()
            }

            return result.toString()
        }
    }

    @Suppress("DEPRECATION")
    private class DownloadImageTask : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg params: String?): Bitmap? {
            var bitmap: Bitmap? = null
            try {
                var URL = URL(params[0])
                var httpURLConnection = URL.openConnection()
                var inputStreamReader = httpURLConnection.getInputStream()
                bitmap = BitmapFactory.decodeStream(inputStreamReader)

            } catch (e: Exception) {
                when (e) {
                    is MalformedURLException -> e.printStackTrace()
                    is IOException -> e.printStackTrace()
                }
            } finally {
                (null as HttpURLConnection?)?.disconnect()
            }
            return bitmap
        }
    }
    @Suppress("DEPRECATION")
    fun downloadImage(view: View) {

        var task = DownloadImageTask()
        var bitmap: Bitmap? = null
        try {
            bitmap  = task.execute(imageURL).get()
        } catch (e: Exception) {
            when (e) {
                is MalformedURLException -> e.printStackTrace()
                is IOException -> e.printStackTrace()
            }
        }
        imageView.setImageBitmap(bitmap)
    }

    fun cityWeather(view: View) {
        startActivity(Intent(this,L6GuessStarActivity::class.java))
    }
}

