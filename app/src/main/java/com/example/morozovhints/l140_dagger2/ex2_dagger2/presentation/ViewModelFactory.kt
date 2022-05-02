package com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider


/**
 * Фактори создает все вью модели.
 *
 * 1 способ работы даггера с viewModel - работа с отдельными вм.
 * Работает, если мало параметров конструктора и 1 вью модель.
 * Т.е. иначе придется все viewModel проверять.
 *
 * 2 способ - работа с коллекцией.
 *
 * Почему используется провайдер, а не viewmodel?
 * Дело в том, что при работе с несколькими активностями при пересоздании их должны создаваться новые
 * viewModel. В обычном случае создаются те же модели, что и при первом создании.
 * Провайдер позволяет возвращать разные значения viewmodel. При этом это работает как раз только
 * в случае пересоздания активити ( при переворотах все по старому ).
 * Если есть субкомпонент, то аннотация @ApplicationScope не ставится, так как если она используется,
 * то будет 1 фактори на все приложение. Как создавать модель нам известно только при создании активити.
 * Общий скоуп же не учивает эту активити.
 */
//@ApplicationScope
class ViewModelFactory @Inject constructor(
//    private val exampleUseCase: ExampleUseCase

    private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass == ExampleViewModel::class.java) {
//            return ExampleViewModel(exampleUseCase) as T
//        }
//        throw RuntimeException("Unknown ViewModel Class $modelClass")
//    }
        return viewModelProviders[modelClass]?.get() as T
    }
}