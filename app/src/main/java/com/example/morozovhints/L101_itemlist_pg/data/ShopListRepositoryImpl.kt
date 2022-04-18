package com.example.morozovhints.L101_itemlist_pg.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.morozovhints.L101_itemlist_pg.domain.ShopItem
import com.example.morozovhints.L101_itemlist_pg.domain.ShopListRepository

/**
 * Синглтон реализации репозитория Domain слоя.
 * Наследует интерфейс по работе с данным Domain слоя.
 * Переопределяет методы репозитория Domain слоя, реализуя методы.
 */
class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val shopListDAO: ShopListDAO = AppDataBase.getInstance(application).shopListDao()
    private val shopListMapper = ShopListMapper()

//    init {
//        for (i in 0 until 20) {
//            val item = ShopItem(name = "name $i", count = i, enabled = Random.nextBoolean())
//            addShopItem(item)
//        }
//    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDAO.addShopItem(shopListMapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDAO.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDAO.addShopItem(shopListMapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDAO.getShopItem(shopItemId)
        return shopListMapper.mapDbModelToEntity(dbModel)

    }


    override fun getShopList(): LiveData<List<ShopItem>> =
        //метод аналогичный медиатору ниже. Но сжат в отдельный метод.
        Transformations.map(shopListDAO.getShopList()) {
            shopListMapper.mapListDbModelToListEntity(it)}

//        //Медиатор! Посредник между liveData и конечным элементом - перехватывает события и реагирует на них.
//        MediatorLiveData<List<ShopItem>>.apply {
//        //функция перехватывет значения и делает с ним чё то. У нас превращает в обычный ShopItem.
//        addSource(shopListDAO.getShopList()){
//          value = shopListMapper.mapListDbModelToListEntity(it)
//        }
//

}