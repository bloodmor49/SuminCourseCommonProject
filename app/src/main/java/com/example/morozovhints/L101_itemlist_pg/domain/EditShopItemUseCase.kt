package com.example.morozovhints.L101_itemlist_pg.domain

class EditShopItemUseCase (private val shopListRepository: ShopListRepository){
    suspend fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}