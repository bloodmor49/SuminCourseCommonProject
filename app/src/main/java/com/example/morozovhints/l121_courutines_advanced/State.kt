package com.example.morozovhints.l121_courutines_advanced

sealed class State()

object Error : State()
object Progress : State()
class Result(
    val factorial: String,
) : State()