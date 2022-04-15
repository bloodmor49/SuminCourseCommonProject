package com.example.morozovhints.l110.domain.usecase

import com.example.morozovhints.l110.domain.entity.Question
import com.example.morozovhints.l110.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {
    //нет смысла давать методу имя как у юзкейса, поэтому используется оператор
    //  аналог generateQuestionUseCase.invoke() - вызов метода без создания экземпляра вручную.
    operator fun invoke(maxSumValue: Int):Question {
        return repository.generateQuestion(maxSumValue,COUNT_OF_OPTIONS)
    }

    private companion object{
        private const val COUNT_OF_OPTIONS = 4
    }
}