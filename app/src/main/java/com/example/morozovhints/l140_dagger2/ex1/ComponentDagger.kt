package com.example.morozovhints.l140_dagger2.ex1

import dagger.Component

/**
 * Компонент даггер. Класс предоставляет реализацию зависимостей или инжектит их в поля классов.
 * Те же 2 метода. Сам даггер формирует инъекции.
 * Делаем аннотацию компонент, а сам класс в виде интерфейса, т.к. вся работа происходит под капотом.
 * ЕЩЁ РАЗ - РЕАЛИЗАЦИЮ ПИСАТЬ НЕ НУЖНО - ОНА ДЕЛАЕТСЯ ПОД КАПОТОМ.
 * Также нужно учесть модули с провайдерами.
 */
@Component(modules = [ComputerModule::class])
interface ComponentDagger {

//    fun newKeyboard(): Keyboard
//    fun newMouse(): Mouse
//    fun newMonitor(): Monitor

    fun inject(activity: MainActivityDependencies)
}

