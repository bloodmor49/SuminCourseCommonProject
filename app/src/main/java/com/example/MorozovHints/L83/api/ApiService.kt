package com.example.MorozovHints.L83.api

import com.example.MorozovHints.L83.pojo.EmployerResponse
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

/**
 * Методы работы с JSON (Retrofit). [getEmployers] - получаем список работяг.
 */
interface ApiService {

    @GET("testTask.json")
    fun getEmployers() : Observable<EmployerResponse>

}