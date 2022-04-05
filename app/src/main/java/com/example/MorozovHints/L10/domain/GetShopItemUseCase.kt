package com.example.MorozovHints.L10.domain

class GetShopItemUseCase (private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopItemId: Int):ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}