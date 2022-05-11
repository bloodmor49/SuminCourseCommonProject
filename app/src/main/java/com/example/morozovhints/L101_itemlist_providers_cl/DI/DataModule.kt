package com.example.morozovhints.L101_itemlist_providers_cl.DI

import android.app.Application
import com.example.morozovhints.L101_itemlist_providers_cl.data.AppDataBase
import com.example.morozovhints.L101_itemlist_providers_cl.data.ShopListDao
import com.example.morozovhints.L101_itemlist_providers_cl.data.ShopListRepositoryImpl
import com.example.morozovhints.L101_itemlist_providers_cl.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @AppScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @AppScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao{
            return AppDataBase.getInstance(application).shopListDao()
        }

    }
}