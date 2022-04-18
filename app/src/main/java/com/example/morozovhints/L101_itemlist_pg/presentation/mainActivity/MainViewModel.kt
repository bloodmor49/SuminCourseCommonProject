package com.example.morozovhints.L101_itemlist_pg.presentation.mainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.morozovhints.L101_itemlist_pg.data.ShopListRepositoryImpl
import com.example.morozovhints.L101_itemlist_pg.domain.DeleteShopItemUseCase
import com.example.morozovhints.L101_itemlist_pg.domain.EditShopItemUseCase
import com.example.morozovhints.L101_itemlist_pg.domain.GetShopListUseCase
import com.example.morozovhints.L101_itemlist_pg.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Основная ViewModel главной активити.
 * Использует три метода бизнес логики по отношению к бизнес модели.
 * Задействованы корутины.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    //создаем дата репозиторий по работе с данными
    private val repository = ShopListRepositoryImpl(application)
    //создаем юзкейсы бизнес логики.
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    //корутины должны входить в состав объектов с ЖЦ. Т.е. в scope. Мы можем создать свой scope.
    //сorutine context показывает где это происходит.
    //Dispatchers - где выполняются корутины. какой поток.
    // Main - главный поток.
    // IO - поток для чтения и записи.
    // Default - сложные вычисления потоки макс производительности как исходя из числа ядер.
    private val scope = CoroutineScope(Dispatchers.IO)

    //создаем просматриваемый список предметов
    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        scope.launch { deleteShopItemUseCase.deleteShopItem(shopItem) }

    }

    fun changeEnableState(shopItem: ShopItem) {
        scope.launch {val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)}
    }


    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}