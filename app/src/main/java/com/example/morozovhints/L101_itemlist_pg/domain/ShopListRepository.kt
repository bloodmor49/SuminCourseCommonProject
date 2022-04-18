package com.example.morozovhints.L101_itemlist_pg.domain

import androidx.lifecycle.LiveData

/**
 * Репозиторий - интерфейс по работе с данными.
 * Яляется абстрактным черным ящиком, который описывает методы работы с бизнес логикой.
 * Наследуется data классами для определения.
 * НЕЗАВИСИТ ОТ DATA.
 */
interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItem(shopItemId: Int):ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}