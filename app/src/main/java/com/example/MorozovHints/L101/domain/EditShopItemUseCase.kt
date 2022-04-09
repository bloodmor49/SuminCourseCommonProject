package com.example.MorozovHints.L101.domain

class EditShopItemUseCase (private val shopListRepository: ShopListRepository){
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}