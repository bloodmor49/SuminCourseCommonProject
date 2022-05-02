package com.example.morozovhints.l140_dagger2.ex1

/**
 * Правильно - зависимости отправляются в конструктор.
 * Т.Е. системный блок передается в конструкторе, а не создается внутри класса.
 */
class ComputerTower (
    val storage : Storage,
    val memory : Memory,
    val processor : Processor
)


///**
// * ComputerTower фактически создает то, что ему нужно.
// * Это не правильно.
// */
//class ComputerTower {
//
//    val storage = Storage()
//    val memory = Memory()
//    val processor = Processor()
//}