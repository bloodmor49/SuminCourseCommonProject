package com.example.morozovhints.L101_itemlist_pg.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
       return shopListRepository.getShopList()
    }

}