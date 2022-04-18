package com.example.morozovhints.L101_itemlist_pg.domain

class DeleteShopItemUseCase (private val shopListRepository: ShopListRepository) {
    suspend fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}