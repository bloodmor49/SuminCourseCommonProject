package com.example.MorozovHints.L110.domain.usecase

import com.example.MorozovHints.L110.domain.entity.GameSettings
import com.example.MorozovHints.L110.domain.entity.Level
import com.example.MorozovHints.L110.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke (level:Level): GameSettings{
        return repository.getGameSettings(level)
    }
}