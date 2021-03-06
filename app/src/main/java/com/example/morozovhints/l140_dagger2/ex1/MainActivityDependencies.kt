package com.example.morozovhints.l140_dagger2.ex1

import android.app.Activity
import com.example.morozovhints.l140_dagger2.ex1.di.DaggerComponentDagger
import com.example.morozovhints.l140_dagger2.ex1.notSimpleParts.Computer
import com.example.morozovhints.l140_dagger2.ex1.simpleParts.Keyboard
import com.example.morozovhints.l140_dagger2.ex1.simpleParts.Monitor
import com.example.morozovhints.l140_dagger2.ex1.simpleParts.Mouse
import javax.inject.Inject

/**
 * Активити зависима от Компьютера, в то время как компьютер является зависимостью активити.
 * Активити создает объекты зависимостей, которые ей нужны.
 * Т.е. каждый класс создает в себе другие классы, от которых он зависит.
 * Это неправильно.
 *
 * Решение этой проблемы - инъекция зависимостей. Это - шаблон проектирования, принцип как строить
 * приложения.
 *
 * !!!!!!!!!!!!!ВСЕ ЗАВИСИМОСТИ ДЛЯ КЛАССА ДОЛЖНЫ ПОСТАВЛЯТЬСЯ ИЗВНЕ!!!!!!!!!
 *
 * Способы без использования библиотек:
 *
 *                      1 способ - инъекция в конструктор.
 *
 * Самый популярный - инъекция конструктора. Все зависимости желательно внедрять в пар-ы конструктору.
 * В данном случае также проблема в том, что получается много элементов, поэтому создается
 * класс Component, в котором есть функция для создания объекта в активити.
 *
 *                      2 способ - инъекция из компонента.
 *
 * Когда нет доступка к конструктору.
 * 2.1 - Класс запрашивает зависимость из Component. Но не очень хорошо.
 * 2.2 - Сделать inject из компонента. Т.е. внутрки компонента создаем inject необходимых
 * зависимостей, а в классе просто вызываем этот метод из компонента, и он сам заполняет все
 * нужные параметры.
 *
 *    Dagger 2 - инъекция зависимостей.
 *
 *  Библиотека выполняет многие вещи выше под капотом.
 *  Внизу пример инъекции клавиатуры.
 *  В клавиатуре аннотация @Inject constructor.
 *  Потом делаем build и формируем инъекцию.
 *
 *  Также оба метода работают:
 *  1. Get через get.
 *  2. Inject через inject.
 *
 * Inject нужно навешивать на все элементы, которые участвуют в @Inject, чтобы даггер знал как всё
 * сделать.
 */
class MainActivityDependencies: Activity() {
//    :
//} AppCompatActivity() {

    //говорим, что объект будет проинициализирован
//    lateinit var computer: Computer

    //слева появляется ссылка на инъекцируемый объект - класс.
    //т.е. че нужно инджектить. Сам даггер вставляет значения во все поля с аннотацией inject.
    //классы для инжекта формируются с конструкторной аннотацией инжект.
    @Inject
    lateinit var keyboard: Keyboard
    @Inject
    lateinit var mouse: Mouse
    @Inject
    lateinit var monitor: Monitor

    //У компьютера есть конструкторы. Даггер не умеет создавать системный блок, поэтому даггеру
    //нужно знать как создавать все зависимости
    @Inject
    lateinit var computer: Computer

    //Внедряем компонет.
    init {
        DaggerComponentDagger.create().inject(this)
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_dependencies)

        //                  ВРУЧНУЮ

        //добавляем все необходимые зависимости в данный класс.
        //куда именно? указанно в компоненте.
        //в данный момент заполняется компьютер.

//        Component().inject(this)

        //              ЧЕРЕЗ ДАГГЕР 2

        // Аналог get
//        DaggerComponentDagger.create().newKeyboard()

        //из java generated. Аналог inject.
//        DaggerComponentDagger.create().inject(this)

//    }




    //в итоге все ограничивается отдельной функцией без тонны текста, за которую отвечает класс
    //component. Это не единственный способ, он часто используется, но он не слишком хороший, так
    //как активити сама запрашивает зависимость. Правильно было бы, если бы в актвивити извне
    //забрасывались зависимости.

//    val computer : Computer = Component().getComputer()


//для создания компьютера приходится создавать кучу других зависимостей - это плохо,
    //т.к. получается слишком дохрена переменных. Поэт
//    val monitor = Monitor()
//    val keyboard = Keyboard()
//    val mouse = Mouse()
//    val computerTower = ComputerTower(
//        storage = Storage(),
//        memory = Memory(),
//        processor = Processor()
//    )
//    val computer = Computer(
//        monitor = monitor,
//        keyboard = keyboard,
//        mouse = mouse,
//        computerTower = computerTower
//    )
}