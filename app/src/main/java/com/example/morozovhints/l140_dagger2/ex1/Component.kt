package com.example.morozovhints.l140_dagger2.ex1

/**
 * Вспомогательный класс для activity. Название - в dagger 2 такой же, поэтому и тут также.
 * Он создает объект компьютер.
 */
class Component {

    //функция нужна для того, чтобы внедрять зависимости ИЗВНЕ.
    //а не запрашивать их изнутри.
//    fun inject(activity:MainActivityDependencies) {
//        activity.computer = getComputer()
//    }

    fun getComputer(): Computer {
        val monitor = Monitor()
        val keyboard = Keyboard()
        val mouse = Mouse()
        val computerTower = ComputerTower(
            storage = Storage(),
            memory = Memory(),
            processor = Processor()
        )
        return Computer(monitor, computerTower, mouse, keyboard)
    }
}