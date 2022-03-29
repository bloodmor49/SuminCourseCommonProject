package com.example.MorozovHints.L83.pojo

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