package com.example.morozovhints.L101_itemlist_providers_cl.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.morozovhints.L101_itemlist_providers_cl.data.AppDataBase.Companion.getInstance

/**
 * Методы работы с бд.
 */
@Dao
interface ShopListDao {
    @Query("SELECT * FROM shop_items")
    //так как liveData происходит в другом потоке, то нет нужды делать suspend
    fun getShopList(): LiveData<List<ShopItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDBModel: ShopItemDBModel)

    @Query("DELETE FROM shop_items WHERE id =:shopItemId")
    suspend fun deleteShopItem(shopItemId: Int)

    @Query("SELECT * FROM shop_items WHERE id=:shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId: Int): ShopItemDBModel

    //Для Content Provider.
    @Query("SELECT * FROM shop_items")
    fun getShopListCursor(): Cursor
}