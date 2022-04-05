package com.example.MorozovHints.L10.domain

/**
 * POJO объект предмета магазина
 */
data class ShopItem(
    var id: Int = UNDEFINED_ID,
    val name: String = "",
    val count: Int = 0,
    val enabled: Boolean = true,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}