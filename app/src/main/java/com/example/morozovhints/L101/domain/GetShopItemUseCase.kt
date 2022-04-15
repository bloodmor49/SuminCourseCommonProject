package com.example.morozovhints.L101.domain

class GetShopItemUseCase (private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopItemId: Int):ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}