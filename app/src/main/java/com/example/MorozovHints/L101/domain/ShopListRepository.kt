package com.example.MorozovHints.L101.domain

import androidx.lifecycle.LiveData

/**
 * Репозиторий - интерфейс по работе с данными.
 * Яляется абстрактным черным ящиком, который описывает методы работы с бизнес логикой.
 * Наследуется data классами для определения.
 * НЕЗАВИСИТ ОТ DATA.
 */
interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItem(shopItemId: Int):ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}