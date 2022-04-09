package com.example.MorozovHints.L101.domain

class DeleteShopItemUseCase (private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}