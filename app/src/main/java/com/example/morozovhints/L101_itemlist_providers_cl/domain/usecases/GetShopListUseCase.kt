package com.example.morozovhints.L101_itemlist_providers_cl.domain.usecases

import androidx.lifecycle.LiveData
import com.example.morozovhints.L101_itemlist_providers_cl.domain.entities.ShopItem
import com.example.morozovhints.L101_itemlist_providers_cl.domain.ShopListRepository
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
       return shopListRepository.getShopList()
    }

}