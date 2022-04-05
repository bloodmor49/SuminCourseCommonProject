package com.example.MorozovHints.L10.domain

class EditShopItemUseCase (private val shopListRepository: ShopListRepository){
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}