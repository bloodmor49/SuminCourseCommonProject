package com.example.morozovhints.L101_itemlist_providers_cl.domain

import javax.inject.Inject

/**
 * USECASE - принцип SOLID - 1 класс = 1 задача бизнес логики.
 * Берет методы интерфейс репозитория [ShopListRepository] без реализации.
 * Для PRESENTATION слоя вводится IMPL класс даты.
 */
class AddShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}