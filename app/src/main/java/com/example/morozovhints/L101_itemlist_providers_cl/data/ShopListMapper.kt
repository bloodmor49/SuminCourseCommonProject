package com.example.morozovhints.L101_itemlist_providers_cl.data

import com.example.morozovhints.L101_itemlist_providers_cl.domain.ShopItem
import javax.inject.Inject

/**
 * Маппер - нужен для того, чтобы подгонять форматы из Domain в data и наоборот.
 * Конкретно данный маппер работает с сущностью ShopItem и используется в импл. репозитории.
 */
class ShopListMapper @Inject constructor() {

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDBModel {
        return ShopItemDBModel(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled,
        )
    }

    fun mapDbModelToEntity(shopItemDBModel: ShopItemDBModel): ShopItem {
        return ShopItem(
            id = shopItemDBModel.id,
            name = shopItemDBModel.name,
            count = shopItemDBModel.count,
            enabled = shopItemDBModel.enabled,
        )
    }

    fun mapListDbModelToListEntity(list: List<ShopItemDBModel>) =
        list.map { mapDbModelToEntity(it) }

}