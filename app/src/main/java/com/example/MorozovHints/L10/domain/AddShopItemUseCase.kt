package com.example.MorozovHints.L10.domain

/**
 * USECASE - принцип SOLID - 1 класс = 1 задача бизнес логики.
 * Берет методы интерфейс репозитория [ShopListRepository] без реализации.
 * Для PRESENTATION слоя вводится IMPL класс даты.
 */
class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}