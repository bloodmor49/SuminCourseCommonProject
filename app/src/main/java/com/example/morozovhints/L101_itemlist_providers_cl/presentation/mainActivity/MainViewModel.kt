package com.example.morozovhints.L101_itemlist_providers_cl.presentation.mainActivity

import androidx.lifecycle.ViewModel
import com.example.morozovhints.L101_itemlist_providers_cl.domain.usecases.DeleteShopItemUseCase
import com.example.morozovhints.L101_itemlist_providers_cl.domain.usecases.EditShopItemUseCase
import com.example.morozovhints.L101_itemlist_providers_cl.domain.usecases.GetShopListUseCase
import com.example.morozovhints.L101_itemlist_providers_cl.domain.entities.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Основная ViewModel главной активити.
 * Использует три метода бизнес логики по отношению к бизнес модели.
 * Задействованы корутины.
 */
class MainViewModel @Inject constructor(
    private val getShopListUseCase : GetShopListUseCase,
    private val deleteShopItemUseCase : DeleteShopItemUseCase,
    private val editShopItemUseCase : EditShopItemUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        scope.launch { deleteShopItemUseCase.deleteShopItem(shopItem) }

    }

    fun changeEnableState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }


    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}