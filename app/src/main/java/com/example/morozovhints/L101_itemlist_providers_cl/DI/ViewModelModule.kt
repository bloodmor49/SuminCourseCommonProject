package com.example.morozovhints.L101_itemlist_providers_cl.DI

import androidx.lifecycle.ViewModel
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.mainActivity.MainViewModel
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.shopItemActivity.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(viewModel: ShopItemViewModel):ViewModel


}