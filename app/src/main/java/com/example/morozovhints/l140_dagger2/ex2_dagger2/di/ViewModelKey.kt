package com.example.morozovhints.l140_dagger2.ex2_dagger2.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Аннотация - ключ к коллекции map вьюмоделей.
 * Нужна для релизной сборки, так как все названия классов изменятся, а ключи нет.
 * Это приведет к коллизиям и ошибкам сборки.
 * В качестве значений - класс viewModel.
 * Это то, что очень часто встречается на реальных проектах.
 * Используется вместо stringkey в [ViewModelModule].
 */
@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
