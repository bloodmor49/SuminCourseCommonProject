package com.example.MorozovHints.L10.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//неправильно, так как зависит от data. Но инъексция не изучена, поэтому пока так.
import com.example.MorozovHints.L10.data.ShopListRepositoryImpl
///////////////////////
import com.example.MorozovHints.L10.domain.DeleteShopItemUseCase
import com.example.MorozovHints.L10.domain.EditShopItemUseCase
import com.example.MorozovHints.L10.domain.GetShopListUseCase
import com.example.MorozovHints.L10.domain.ShopItem

class MainViewModel : ViewModel() {

    //создаем дата репозиторий по работе с данными
    private val repository = ShopListRepositoryImpl
    //создаем юзкейсы бизнес логики.
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    //создаем просматриваемый список предметов
    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }



}