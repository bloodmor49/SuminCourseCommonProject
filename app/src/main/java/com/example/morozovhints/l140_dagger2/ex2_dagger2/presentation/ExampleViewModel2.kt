package com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation

import androidx.lifecycle.ViewModel
import com.example.morozovhints.l140_dagger2.ex2_dagger2.domain.ExampleUseCase
import javax.inject.Inject

class ExampleViewModel2 @Inject constructor(private val useCase: ExampleUseCase) : ViewModel() {
    fun method() {
        useCase()
    }
}