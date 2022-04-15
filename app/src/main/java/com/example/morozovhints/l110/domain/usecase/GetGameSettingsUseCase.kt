package com.example.morozovhints.l110.domain.usecase

import com.example.morozovhints.l110.domain.entity.GameSettings
import com.example.morozovhints.l110.domain.entity.Level
import com.example.morozovhints.l110.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke (level:Level): GameSettings{
        return repository.getGameSettings(level)
    }
}