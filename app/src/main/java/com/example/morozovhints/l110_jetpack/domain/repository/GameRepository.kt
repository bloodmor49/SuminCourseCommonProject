package com.example.morozovhints.l110_jetpack.domain.repository

import com.example.morozovhints.l110_jetpack.domain.entity.GameSettings
import com.example.morozovhints.l110_jetpack.domain.entity.Level
import com.example.morozovhints.l110_jetpack.domain.entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}