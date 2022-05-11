package com.example.morozovhints.L101_itemlist_providers_cl.DI

import android.app.Application
import com.example.morozovhints.L101_itemlist_providers_cl.data.ShopListProvider
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.ShopItemFragment
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.mainActivity.ShopListMainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: ShopListMainActivity)

    fun inject(fragment: ShopItemFragment)

    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }
}