package com.example.MorozovHints.L81.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * Характеристики работника (POJO)
 */
data class Employer (

    @SerializedName("f_name")
    @Expose
    var fName: String? = null,

    @SerializedName("l_name")
    @Expose
    var lName: String? = null,

    @SerializedName("birthday")
    @Expose
    var birthday: String? = null,

    @SerializedName("avatr_url")
    @Expose
    var avatrUrl: String? = null,

    @SerializedName("specialty")
    @Expose
    var specialty: List<Specialty?>? = null
)