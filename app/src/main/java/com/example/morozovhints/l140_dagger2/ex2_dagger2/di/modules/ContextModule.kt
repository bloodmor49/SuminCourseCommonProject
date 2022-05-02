package com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Работа с контекстом.
 * !!!Класс не нужен, если есть кастомный контект в компоненте. (Component.Build)!!!
 */
@Module
class ContextModule(private val application: Application) {
    @Provides
    fun provideContext():Context{
        return application
    }
}