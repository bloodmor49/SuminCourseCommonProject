package com.example.morozovhints.l140_dagger2.ex2_dagger2.domain

import javax.inject.Inject

class ExampleUseCase @Inject constructor(private val repository: ExampleRepository) {

    operator fun invoke() {
        repository.method()
    }
}