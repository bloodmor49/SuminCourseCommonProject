package com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules

import androidx.lifecycle.ViewModel
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.ViewModelKey
import com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation.ExampleViewModel
import com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation.ExampleViewModel2
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Для нескольких вьюмоделей.
 * Даггер берет все модели и закидывает их в список ключ(строка имя) - значение(вьюмодель)
 */
@Module
interface ViewModelModule {

    //В мап
    @IntoMap
    //с таким ключом. Проблема ключа в том, что при билде для маркета его значение не поменяется.
    // А название модели будет закодировано, что вызовет ошибки. Для решения проблемы нужен
    // [ViewModelKey]
//    @StringKey("ExampleViewModel")
    @ViewModelKey(ExampleViewModel::class)
    //привязка модели к реализации
    @Binds
    fun bindExampleViewModel(impl: ExampleViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ExampleViewModel2::class)
    @Binds
    fun bindExampleViewModel2(impl: ExampleViewModel2): ViewModel
}