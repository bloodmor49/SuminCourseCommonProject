package com.example.MorozovHints.L83.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.MorozovHints.L83.converters.Converter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Характеристики работника (POJO)
 */
@Entity(tableName = "Employers")
@TypeConverters(value = [Converter::class])
data class Employer (

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

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