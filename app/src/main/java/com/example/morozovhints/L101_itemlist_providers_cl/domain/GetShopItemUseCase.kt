package com.example.morozovhints.L101_itemlist_providers_cl.domain

import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int):ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}