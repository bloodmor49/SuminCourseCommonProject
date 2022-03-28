package com.example.messenger.L81.api

import com.example.messenger.L81.pojo.EmployerResponse
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

/**
 * Методы работы с JSON (Retrofit). [getEmployers] - получаем список работяг.
 */
interface ApiService {

    @GET("testTask.json")
    fun getEmployers() : Observable<EmployerResponse>

}