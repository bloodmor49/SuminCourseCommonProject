package com.example.morozovhints.L081_training_jun_task_retrofit_json.api

import com.example.morozovhints.L081_training_jun_task_retrofit_json.pojo.EmployerResponse
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

/**
 * Методы работы с JSON (Retrofit). [getEmployers] - получаем список работяг.
 */
interface ApiService {

    @GET("testTask.json")
    fun getEmployers() : Observable<EmployerResponse>

}