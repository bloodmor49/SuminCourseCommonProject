package com.example.messenger.L72.db

import android.provider.BaseColumns


/**
 * Класс для определения констант для db SQLite. Содержит Companion Object со всеми константами,
 * а также командами по созданию и удалению таблицы. Наследует класс BaseColumns.
 */
class NotesContact {

    companion object : BaseColumns {

        // О пределяем константсы.
        // Это нужно, так как они часто вызываются и так меньше шансов ошибиться.

        const val TABLE_NAME = "notes"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DAY_OF_WEEK = "day_of_week"
        const val COLUMN_PRIORITY = "priority"
        const val ID = BaseColumns._ID

        const val TYPE_TEXT = "TEXT"
        const val TYPE_INTEGER = "INTEGER"

        //создает таблицу
        const val CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " " + TYPE_TEXT + ", " +
                COLUMN_DESCRIPTION + " " + TYPE_TEXT + ", " +
                COLUMN_DAY_OF_WEEK + " " + TYPE_INTEGER + ", " +
                COLUMN_PRIORITY + " " + TYPE_INTEGER + ")"

        //удаляет таблицу
        const val DROP_COMMAND = "DROP TABLE IF EXIST $TABLE_NAME"
    }
}