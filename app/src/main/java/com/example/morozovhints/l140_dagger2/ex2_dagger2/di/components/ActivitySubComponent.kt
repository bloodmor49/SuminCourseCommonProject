package com.example.morozovhints.l140_dagger2.ex2_dagger2.di.components

import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules.ViewModelModule
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.qualifiers.IdQualifier
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.qualifiers.NameQualifier
import com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation.MainActivity2Dagger2
import com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation.MainActivityDagger2
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Субкомпонент от [ApplicationComponenti].
 * Разница от компонента в том, что его может создавать только компонент,в котором он находится.
 * id существует только в момент создания экрана. Этот компонент создается после создания экрана.
 * Основной компонент создается только при build. В этом разница. Субкомпонент является дочерним
 * от application. Он может взять у родителя функции и остальное, но не наоборот. (аля наследование)
 * Время жизни у него как и у активити.
 * Application компонет мы создавали сами.
 */
@Subcomponent(
    modules = [ViewModelModule::class]
)
interface ActivitySubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            //Способ чере аннотацию @Named
//            @BindsInstance @Named("id") id: String,
//            @BindsInstance @Named("name")name: String
            //Способ чере Qualifier
            @BindsInstance @IdQualifier id: String,
            @BindsInstance @NameQualifier name: String
        ): ActivitySubComponent
    }

    fun inject(activity: MainActivityDagger2)
    fun inject(activity: MainActivity2Dagger2)
}