package com.example.morozovhints.L101_itemlist_pg.domain

class GetShopItemUseCase (private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int):ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}