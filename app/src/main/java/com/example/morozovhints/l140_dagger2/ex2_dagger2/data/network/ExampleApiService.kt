package com.example.morozovhints.l140_dagger2.ex2_dagger2.data.network

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Апи сервис для работы с сетью. Inject.
 * Dagger позволяет применять паттерн синглтон через аннотацию.
 * Синглтон используется время жизни Scope, поэтому все его компоненты должны быть с такой же аннотацией.
 * Т.е. сам компонент создает зависимость и там тоже нужна аннотация.
 * В рамках жизни одного application компонента будет жить 1 экз. базы данных.
 * Если создаем в активити, то при повороте будет создана новая БД.
 * Нужно, чтобы 1 БД сохранялась и не менялась.
 * Синглтон в данном случае должен быть глобальным, т.е. scope должен существовать всю жизнь приложения.
 * Для этого создаем [L140Application], где и создаем наш экземпляр компонента.
 */
@Singleton
class ExampleApiService @Inject constructor(
    private val currentTime: Long
) {
    fun method() {
        Log.d(LOG_TAG, "ExampleApiService: $currentTime")
    }

    companion object {

        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}