package com.example.morozovhints.L072_sqlite_room.ViewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.morozovhints.L072_sqlite_room.RoomDB.Note
import com.example.morozovhints.L072_sqlite_room.RoomDB.NotesDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val database = NotesDatabase.getInstance(application)

    private var notes = database?.notesDao()?.getAllNotes()

    fun getNotes(): LiveData<MutableList<Note>>? {
        return notes
    }

    fun insertNote(note: Note) {
        database?.let { InsertTask(it).execute(note) }
    }

    fun deleteNote(note: Note) {
        database?.let { DeleteTask(it).execute(note) }
    }

    fun deleteAllNotes() {
        database?.let { DeleteAllTask(it).execute() }
    }

    @Suppress("DEPRECATION")
    class InsertTask(val database : NotesDatabase) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note?): Void? {
            if (params.isNotEmpty()) {
                params[0]?.let { database.notesDao().insertNote(it) }
            }
            return null
        }
    }

    @Suppress("DEPRECATION")
    class DeleteTask(val database : NotesDatabase) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note?): Void? {
            if (params.isNotEmpty()) {
                params[0]?.let { database.notesDao().deleteNote(it) }
            }
            return null
        }
    }

    @Suppress("DEPRECATION")
    class DeleteAllTask(val database : NotesDatabase) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            if (params.isNotEmpty()) {
                params[0]?.let { database.notesDao().deleteAllNotes() }
            }
        return null
        }
    }

}