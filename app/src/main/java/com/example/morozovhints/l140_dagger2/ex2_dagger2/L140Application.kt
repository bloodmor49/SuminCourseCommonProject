package com.example.morozovhints.l140_dagger2.ex2_dagger2

import android.app.Application
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.components.DaggerApplicationComponent

/**
 * Экземпляр приложения. Нужен для создания глоабльного синглтона Даггера.
 * Вызывается при создании инъекциях в мейн активити даггер 2.
 * Не забываем сообщить приложению в манифесте!
 */
class L140Application: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this,System.currentTimeMillis())
    }
}