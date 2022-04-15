package com.example.morozovhints.L102.domain

data class Item(
    val id: Int = UNDEFINED_ID,
    val name: String = "",
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}