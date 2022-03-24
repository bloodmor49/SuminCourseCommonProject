package com.example.messenger.L72.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


/**
 * Интерфейс для работы с ДБ. (Dao - data access object). Язык SQL.
 */
@Dao
interface NotesDao {

    @Query("SELECT * FROM notesROOM")
    fun getAllNotes(): MutableList<Note>

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM notesROOM")
    fun deleteAllNotes()
}