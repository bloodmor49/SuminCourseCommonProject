package com.example.messenger.L72.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Основной класс для работы с DB SQLITE. Берет константы из NotesContact. Переопределяет два метода
 * onCreate - при первом открытии дата базы, а также onUpgrade - при последующих обновлениях.
 */
class NotesDBHelper(
    context: Context?,
    name: String? = DB_NAME,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = DB_VERSION,
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        const val DB_NAME = "notes.db"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(NotesContact.CREATE_COMMAND) //создаем db
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(NotesContact.DROP_COMMAND) //удаляет существующую таблицу
        onCreate(db) //создает новую таблицу
    }
}