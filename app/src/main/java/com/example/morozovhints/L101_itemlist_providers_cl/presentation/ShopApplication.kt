package com.example.morozovhints.L101_itemlist_providers_cl.presentation

import android.app.Application
import com.example.morozovhints.L101_itemlist_providers_cl.DI.DaggerAppComponent

class ShopApplication: Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}