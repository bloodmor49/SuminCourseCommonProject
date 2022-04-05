package com.example.MorozovHints.L10.domain

class DeleteShopItemUseCase (private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}