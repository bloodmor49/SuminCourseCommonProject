package com.example.morozovhints.l140_dagger2.ex2_dagger2.di.components

import android.content.Context
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.ApplicationScope
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules.DataModule
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules.DomainModule
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules.ViewModelModule
import com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation.MainActivity2Dagger2
import com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation.MainActivityDagger2
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * У компонента метод create есть в том случае, если конструктор пустой.
 * В противном случае необходимо самим вызывать билдер и вручную создавать модули.
 * Это нужно если необходимо передавать аргументы.
 * Если компонент не знает как создать зависимость, то он обращается к модулям и ищет это там.
 * Так как [ExampleApiService] - синглтон, то и здесь нужна эта аннотация.
 * Датабаза у нас со своим scope [ApplicationScope], поэтому его аннотацию тоже надо добавить.
 * Её также можно вешать на binds методы.
 *
 * Создает граф зависимости. А также сабкомпоненты с помощью фабрики (вызываем её в активити).
 */
@Singleton
@ApplicationScope
@Component(modules = [
    DomainModule::class,
    DataModule::class,
//  ContextModule::class,
    ViewModelModule::class])
interface ApplicationComponent {

    fun activityComponentFactory(): ActivitySubComponent.Factory

//    fun inject(activity: MainActivityDagger2)
//    fun inject(activity: MainActivity2Dagger2)

    //Наш кастомный билдер, чтобы не вызывать его в активити.
    //Мы об этом говорим даггеру.
//    @Component.Builder
//    interface ApplicationComponentBuilder {
//        fun build(): ApplicationComponent
//
//        //контекст будет передан в компонент.
//        //контекст добавляет метод в граф зависимостей. Теперь классы смогут сами брать контекст от
//        //этой функции. Так что нам теперь вообще не нужен модуль контекста нигде.
//
//        //ЕЩЕ РАЗ. ЕСЛИ НУЖНО ПЕРЕДАТЬ ПАРАМЕТР И ИСПОЛЬЗОВАТЬ В ГРАФЕ ЗАВИСИМОСТЕЙ, ТО
//        //ПРЕДПОЧТИТЕЛЬНЕЙ ДЕЛАТЬ ЕГО С ПОМОЩЬЮ БИЛДЕРА, А НЕ ПЕРЕДАЧИ В КОНСТРУКТОР МОДУЛЯ
//
//        @BindsInstance
//        fun context(context: Context):ApplicationComponentBuilder
//
//        @BindsInstance
//        fun currentTime(currentTime:Long ):ApplicationComponentBuilder
//    }

    //аналог метода выше, более современная штука. Хотя разницы особо нет.
    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance currentTime: Long,
        ): ApplicationComponent
    }

}