package com.example.MorozovHints.L110.domain.entity

/**
 * ENUM класс уровня. Почему не дата? Потому что с перечислением мы точно знаем какие могут
 * быть уровни, т.е. их конечное количество,которое мы задаем изначально.
 */
enum class Level {
    TEST, EASY, NORMAL, HARD
}