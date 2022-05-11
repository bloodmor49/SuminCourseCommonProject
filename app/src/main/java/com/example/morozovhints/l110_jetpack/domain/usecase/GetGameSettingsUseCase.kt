package com.example.morozovhints.l110_jetpack.domain.usecase

import com.example.morozovhints.l110_jetpack.domain.entity.GameSettings
import com.example.morozovhints.l110_jetpack.domain.entity.Level
import com.example.morozovhints.l110_jetpack.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke (level:Level): GameSettings{
        return repository.getGameSettings(level)
    }
}