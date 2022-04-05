package com.example.MorozovHints.L082.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Specialty (
    @SerializedName("specialty_id")
    @Expose
    private var specialtyId: Int = 0,

    @SerializedName("name")
    @Expose
    private var name: String? = null
)