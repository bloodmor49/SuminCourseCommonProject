package com.example.morozovhints.L101_itemlist_providers_cl.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.morozovhints.L101_itemlist_providers_cl.domain.ShopItem
import com.example.morozovhints.L101_itemlist_providers_cl.domain.ShopListRepository
import javax.inject.Inject

/**
 * Синглтон реализации репозитория Domain слоя.
 * Наследует интерфейс по работе с данным Domain слоя.
 * Переопределяет методы репозитория Domain слоя, реализуя методы.
 */
class ShopListRepositoryImpl @Inject constructor(
    private val shopListDao: ShopListDao,
    private val shopListMapper: ShopListMapper,
) : ShopListRepository {

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(shopListMapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(shopListMapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return shopListMapper.mapDbModelToEntity(dbModel)

    }


    override fun getShopList(): LiveData<List<ShopItem>> =
        //метод аналогичный медиатору ниже. Но сжат в отдельный метод.
        Transformations.map(shopListDao.getShopList()) {
            shopListMapper.mapListDbModelToListEntity(it)
        }

//        //Медиатор! Посредник между liveData и конечным элементом - перехватывает события и реагирует на них.
//        MediatorLiveData<List<ShopItem>>.apply {
//        //функция перехватывет значения и делает с ним чё то. У нас превращает в обычный ShopItem.
//        addSource(shopListDao.getShopList()){
//          value = shopListMapper.mapListDbModelToListEntity(it)
//        }
//

}