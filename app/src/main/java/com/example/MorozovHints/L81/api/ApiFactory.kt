package com.example.MorozovHints.L81.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Синглтон для получения данных с сервера и создания объекта для Retrofit.
 */
class ApiFactory {

    companion object {
        private var apiFactory: ApiFactory? = null

        private const val BASE_URL = "https://gitlab.65apps.com/65gb/static/raw/master/"


        //Retrofit - соединение с JSON АПИ СЕРВЕРОМ.
        private var retrofit = Retrofit.Builder()
            //Добавляем преобразователь GSON (GSON -> объект)
            .addConverterFactory(GsonConverterFactory.create())
            //Адаптер необходим для наблюдения за загрузкой из сети (RxJava3 cоздает Observable)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        fun getInstance(): ApiFactory {
            if (apiFactory == null) apiFactory = ApiFactory()
            return apiFactory as ApiFactory
        }

    }

    //получаем интерфейс для работы с сервером. В данном случае берем список работяг.
    fun getApiService():ApiService = retrofit.create(ApiService::class.java)

}