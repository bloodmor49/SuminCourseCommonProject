package com.example.morozovhints.L101.domain

class EditShopItemUseCase (private val shopListRepository: ShopListRepository){
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}