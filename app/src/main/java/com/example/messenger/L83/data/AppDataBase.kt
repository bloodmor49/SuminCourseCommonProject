package com.example.messenger.L83.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messenger.L83.pojo.Employer

@Database(entities = [Employer::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun employersDao(): EmployersDao

    companion object {
        private var db: AppDataBase? = null
        private const val DB_NAME = "employers.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration() //удаление старой дб
                    //.allowMainThreadQueries()
                    .build()
                db = instance
                return instance
            }

        }
    }
}