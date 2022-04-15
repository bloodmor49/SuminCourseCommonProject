package com.example.morozovhints.l110.domain.entity

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>,
) {
    val rightAnswer: Int
        get() = sum - visibleNumber
}