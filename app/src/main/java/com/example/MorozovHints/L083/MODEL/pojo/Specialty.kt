package com.example.MorozovHints.L083.MODEL.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Specialty (
    @SerializedName("specialty_id")
    @Expose
    var specialtyId: Int = 0,

    @SerializedName("name")
    @Expose
    var name: String? = null
)