package com.example.morozovhints.L06

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.morozovhints.R
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

class L6GuessStarActivity : AppCompatActivity() {

    private lateinit var setCityText: String
    private lateinit var textViewCityWeather : TextView
    private lateinit var buttonJsonStart : Button
    private lateinit var editTextTextSetCity : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l6_json_weather)

        buttonJsonStart = findViewById(R.id.buttonJsonStart)
        textViewCityWeather = findViewById(R.id.textViewCityWeather)
        editTextTextSetCity = findViewById(R.id.editTextTextSetCity)

    }


    fun getCityWeather(view: View) {

        setCityText = editTextTextSetCity.text.trim().toString()
        var toJSONText = "https://api.openweathermap.org/data/2.5/weather?q=$setCityText&appid=423a45c52d416f5bd33027a110393520&units=metric&lang=ru"
        var task = DownloadContentTask()
        try {
            var result = task.execute(toJSONText).get()
            Log.i("URL", result)


            var jsonObject = JSONObject(result)
            var city = jsonObject.getString("name")
            Log.i("URL", "Название города - $city")


            var main = jsonObject.getJSONObject("main")
            var temp = main.getString("temp")
            Log.i("URL","Температура - $temp С`")


            var jsonArray = jsonObject.getJSONArray("weather")
            var weather = jsonArray.getJSONObject(0)
            var weatherDes = weather.getString("description")
            Log.i("URL","Описание погоды - $weatherDes")


            textViewCityWeather.text =
                "Название города - $city\nТемпература - $temp С`\nОписание погоды - $weatherDes"

        } catch (e: Exception) {
            when {
                    (e is ExecutionException) ||
                    (e is InterruptedException) ||
                    (e is JSONException) ->
                    {e.printStackTrace()
                    var toast: Toast = Toast.makeText(this,"Неправильное название города", Toast.LENGTH_LONG)
                    toast.show()}
            }
        }
    }


private class DownloadContentTask : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            var result = StringBuilder()
            try {
                var line: String? = null
                var url = URL(params[0])
                var URLConnection = url.openConnection()
                var inputStream = URLConnection.inputStream
                var inputStreamReader = InputStreamReader(inputStream)
                var bufferedReader = BufferedReader(inputStreamReader)
                line = bufferedReader.readLine()
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

}