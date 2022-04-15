package com.example.morozovhints.L101.presentation.mainActivity

import androidx.lifecycle.ViewModel
import com.example.morozovhints.L101.data.ShopListRepositoryImpl
import com.example.morozovhints.L101.domain.DeleteShopItemUseCase
import com.example.morozovhints.L101.domain.EditShopItemUseCase
import com.example.morozovhints.L101.domain.GetShopListUseCase
import com.example.morozovhints.L101.domain.ShopItem

/**
 * Основная ViewModel главной активити.
 * Использует три метода бизнес логики по отношению к бизнес модели.
 * LiveData - список всех предметов.
 */
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