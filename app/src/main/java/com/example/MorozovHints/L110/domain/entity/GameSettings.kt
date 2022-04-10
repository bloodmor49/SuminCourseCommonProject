package com.example.MorozovHints.L110.domain.entity

data class GameSettings (
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    var minPercentOfRightAnswers: Int,
    var gameTimeInSeconds: Int
        )