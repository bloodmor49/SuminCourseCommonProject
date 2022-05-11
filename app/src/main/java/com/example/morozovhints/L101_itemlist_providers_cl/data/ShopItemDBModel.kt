package com.example.morozovhints.L101_itemlist_providers_cl.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность Shopitem для работы в дата слое, чтобы не задевать domain слой.
 */
@Entity(tableName = "shop_items")
data class ShopItemDBModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String = "",
    val count: Int = 0,
    val enabled: Boolean = true,
)