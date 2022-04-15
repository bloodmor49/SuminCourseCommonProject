package com.example.morozovhints.L072.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс, содержащий информацию о заметках.
 */

//Entity - объект базы данных. Относится к Data Entities.
@Entity(tableName = "notesROOM")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "dayOfWeek")
    var dayOfWeek: String? = null,
    @ColumnInfo(name = "priority")
    var priority: Int? = null,

)
