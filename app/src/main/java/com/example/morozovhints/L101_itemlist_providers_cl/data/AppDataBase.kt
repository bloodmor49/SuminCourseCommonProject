package com.example.morozovhints.L101_itemlist_providers_cl.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * БД Рум. Синглтон. Для получения используйте [getInstance] (Чтобы был только один экземпляр класса)
 */
@Database(entities = [ShopItemDBModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "shop_items"

        fun getInstance(application: Application): AppDataBase {

            //DOUBLE_CHECK - двойная проверка. С синхроизацией, чтобы другие не могли вмешаться
            //одновременно.
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }

        }
    }
}