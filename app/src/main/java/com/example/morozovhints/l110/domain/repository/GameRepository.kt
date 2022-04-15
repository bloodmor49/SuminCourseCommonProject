package com.example.morozovhints.l110.domain.repository

import com.example.morozovhints.l110.domain.entity.GameSettings
import com.example.morozovhints.l110.domain.entity.Level
import com.example.morozovhints.l110.domain.entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}