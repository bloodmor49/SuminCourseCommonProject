package com.example.messenger.L72.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Синглтон базы данных.
 * Содержит метод [getInstance] для создание единственного экземпляра.
 * Также учтен вопрос синхронизации.
 * Имплементация notesDao включена в наследование из интерфейса notesDao
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object {
        private var db: NotesDatabase? = null
        private const val DB_NAME = "notesRoom.db"
        private val LOCK = Any()

        fun getInstance(context: Context): NotesDatabase? {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, NotesDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
                db = instance
                return instance
            }

        }
    }

}

