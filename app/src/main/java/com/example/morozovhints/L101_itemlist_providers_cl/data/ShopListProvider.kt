package com.example.morozovhints.L101_itemlist_providers_cl.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.morozovhints.L101_itemlist_providers_cl.domain.entities.ShopItem
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.ShopApplication
import javax.inject.Inject

class ShopListProvider: ContentProvider() {

    @Inject
    lateinit var shopListDao: ShopListDao

    private val component by lazy {
        (context as ShopApplication).component
    }

    @Inject
    lateinit var shopListMapper: ShopListMapper

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.example.morozovhints","shopItems",GET_SHOP_ITEMS_QUERY)
        //если будет запрос shopitem/4,3,1,100 - решетка все пропустит. Не нужно писать shopItems/4
//        addURI("com.example.morozovhints","shopItems/#",GET_SHOP_ITEMS_QUERY)
    }

    override fun onCreate(): Boolean {

        component.inject(this)

        //Если успешно создан, то возвращаем тру. Иначе фолс.
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        //работаем с матчером. Проверка запросов.
        val code = uriMatcher.match(uri)
        Log.i("myProvider","query: $uri, code: $code")
        return when (code) {
            GET_SHOP_ITEMS_QUERY -> {
                shopListDao.getShopListCursor()
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                if (values == null) return null
                val id = values.getAsInteger("id")
                val name = values.getAsString("name")
                val count = values.getAsInteger("count")
                val enabled = values.getAsBoolean("enabled")
                val shopItem = ShopItem(id, name, count, enabled)
                shopListDao.addShopItemNoAsync(shopListMapper.mapEntityToDbModel(shopItem))
            }

        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                val id = selectionArgs?.get(0)?.toInt() ?: -1
                return shopListDao.deleteShopItemNoAsync(id)
            }
        }
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?,
    ): Int {
        TODO("Not yet implemented")
    }

    companion object{
        private const val GET_SHOP_ITEMS_QUERY = 100
    }
}