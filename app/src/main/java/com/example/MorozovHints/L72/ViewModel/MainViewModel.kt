package com.example.MorozovHints.L72.ViewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.MorozovHints.L72.RoomDB.Note
import com.example.MorozovHints.L72.RoomDB.NotesDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var database = NotesDatabase.getInstance(application)

    private var notes = database?.notesDao()?.getAllNotes()

    fun getNotes(): LiveData<MutableList<Note>>? {
        return notes
    }

    fun insertNote(note: Note) {
        InsertTask().execute(note)
    }

    fun deleteNote(note: Note) {
        DeleteTask().execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllTask().execute()
    }

    @Suppress("DEPRECATION")
    inner class InsertTask : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note?): Void? {
            if (params.isNotEmpty()) {
                params[0]?.let { database?.notesDao()?.insertNote(it) }
            }
            return null
        }
    }

    @Suppress("DEPRECATION")
    inner class DeleteTask : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note?): Void? {
            if (params.isNotEmpty()) {
                params[0]?.let { database?.notesDao()?.deleteNote(it) }
            }
            return null
        }
    }

    @Suppress("DEPRECATION")
    inner class DeleteAllTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            if (params.isNotEmpty()) {
                params[0]?.let { database?.notesDao()?.deleteAllNotes() }
            }
        return null
        }
    }

}