package com.example.morozovhints.L083_retrofit_gson.MODEL.api

import com.example.morozovhints.L083_retrofit_gson.MODEL.pojo.EmployerResponse
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

/**
 * Методы работы с JSON (Retrofit). [getEmployers] - получаем список работяг.
 */
interface ApiService {

    @GET("testTask.json")
    fun getEmployers() : Observable<EmployerResponse>

}