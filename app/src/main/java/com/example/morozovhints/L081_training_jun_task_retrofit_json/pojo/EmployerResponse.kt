package com.example.morozovhints.L081_training_jun_task_retrofit_json.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * Главный класс POJO
 */
data class EmployerResponse (
    @SerializedName("response")
    @Expose
    var response: MutableList<Employer>? = null
)