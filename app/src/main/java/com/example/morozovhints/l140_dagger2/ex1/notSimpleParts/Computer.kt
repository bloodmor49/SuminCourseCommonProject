package com.example.morozovhints.l140_dagger2.ex1.notSimpleParts

import com.example.morozovhints.l140_dagger2.ex1.simpleParts.Keyboard
import com.example.morozovhints.l140_dagger2.ex1.simpleParts.Monitor
import com.example.morozovhints.l140_dagger2.ex1.simpleParts.Mouse
import javax.inject.Inject


/**
 * Компьютер зависим от монитора, сис блока, мыши, клавиатуры.
 *
 * Пример: Для сборки компьютера нужны составные части. В магазин для сборки ПК поставляются его
 * части.
 *
 * Правильно - классу передаются зависимости в конструктор. Он от них зависит, но не создает.
 */
class Computer @Inject constructor (

    val monitor : Monitor,
    val computerTower : ComputerTower,
    val mouse : Mouse,
    val keyboard : Keyboard

)

/**
 * Неправильно - класс компьютер создает зависимости сам внутри себя.
 */
//class Computer {
//    val monitor = Monitor()
//    val computerTower = ComputerTower()
//    val mouse = Mouse()
//    val keyboard = Keyboard()
//
//}