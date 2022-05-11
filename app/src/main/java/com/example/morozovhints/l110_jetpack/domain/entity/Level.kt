package com.example.morozovhints.l110_jetpack.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * ENUM класс уровня. Почему не дата? Потому что с перечислением мы точно знаем какие могут
 * быть уровни, т.е. их конечное количество,которое мы задаем изначально.
 * Serializable - интерфейс, который позволяет объект класса превратить в набор байтов.
 * Enum его реализует.
 */
@Parcelize
enum class Level: Parcelable {
    TEST, EASY, NORMAL, HARD
}