package com.example.morozovhints.l111_fragments_training

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class L111Data(
    val dataString: String = "L111Data.dataString",
    val dataInt: Int = 10,
    val dataBoolean: Boolean = true,
) : Parcelable


