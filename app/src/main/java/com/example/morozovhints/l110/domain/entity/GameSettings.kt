package com.example.morozovhints.l110.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Парселяция VS Сериализация
//
//Сериализация - для переноса классов, перевод в байт код и обратно.
// Под капотом много кода и времени работы. Но не нужно переопределять методы.
// Обычно используется парселяция. Она намного быстрее. Но он содержит абстрактные методы, которые
// надо переопределить. Реализация особо не нужна - есть аннотация, которая позволяет автореализацию.
// по плагину в BuildGradle в плагинах  id 'kotlin-parcelize'. А потом указываем аннотацию. И всё.

@Parcelize
data class GameSettings (
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    var minPercentOfRightAnswers: Int,
    var gameTimeInSeconds: Int
        ) :Parcelable {


//
//    //2. Считываем все поля из данного объекта в том порядке,в котором они в конструкте.
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt()) {
//    }
//
//    //1. Забиваем в пакет нужные данные на передачу.
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(maxSumValue)
//        parcel.writeInt(minCountOfRightAnswers)
//        parcel.writeInt(minPercentOfRightAnswers)
//        parcel.writeInt(gameTimeInSeconds)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    //3. Переопределяются создание объекта и описание создание массива объекта.
//    companion object CREATOR : Parcelable.Creator<GameSettings> {
//        override fun createFromParcel(parcel: Parcel): GameSettings {
//            return GameSettings(parcel)
//        }
//
//        override fun newArray(size: Int): Array<GameSettings?> {
//            return arrayOfNulls(size)
//        }
//    }
}