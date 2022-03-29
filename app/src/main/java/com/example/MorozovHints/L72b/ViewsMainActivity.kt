package com.example.MorozovHints.L72b

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L72b.ViewsMainActivity.Notes.listOfNotes
import com.example.MorozovHints.L72b.db.NotesContact
import com.example.MorozovHints.L72b.db.NotesDBHelper
import com.example.MorozovHints.L72b.recyclerViewAdapter.NotesAdapter
import com.example.MorozovHints.R

open class ViewsMainActivity : AppCompatActivity() {

    object Notes {
        var listOfNotes = mutableListOf<Note>()
    }

    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var sqLiteDatabase: SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l71_views_main)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)

        //                                    РАБОТА С ДБ

        //создаем объект класса дбхелпер
        var dbHelper = NotesDBHelper(this)
        //создаем дата базу с возможностью записи
        sqLiteDatabase = dbHelper.writableDatabase
        getData()

        //                                    РАБОТА С АДАПТЕРОМ RECYCLER VIEW

        adapter = NotesAdapter(listOfNotes)
        recyclerViewNotes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewNotes.adapter = adapter

        adapter.onNoteClickListener = object : NotesAdapter.OnNoteClickListener {
            override fun onNoteClick(position: Int) {
                Toast.makeText(this@ViewsMainActivity, "$position", Toast.LENGTH_LONG).show()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onNoteLongClick(position: Int) {
            }
        }

        //Удаление свайпом
        var simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                removeNote(viewHolder.adapterPosition)
            }

        }
        var itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes)
    }

    //Добавление новой записки
    fun onClickAddNote(view: View) {
        startActivity(Intent(this, AddNoteActivity::class.java))
    }

    //Удаление записки
    @SuppressLint("NotifyDataSetChanged")
    fun removeNote(position: Int) {
        //берем ID нашей конкретной записки. ID записки = ID строки DB
        var id = listOfNotes[position].id
        //Определяем строку в дб для удаления, где ID = ...
        var where = NotesContact.ID + " = ?"
        //берем ID нашей конкретной записки. ID записки = ID строки DB
        var whereArgs = arrayOf(id.toString())
        //Удалить все из таблицы TABLE_NAME, где (where) ID = нашему переданному ID
        sqLiteDatabase.delete(NotesContact.TABLE_NAME, where, whereArgs)
        getData()
        adapter.notifyItemRemoved(position)
    }

    @SuppressLint("Range", "Recycle")
    //Получаем данные из ДБ. При этом список чистим. Пользуемся курсором.
    fun getData() {
        listOfNotes.clear()
        val cursor = sqLiteDatabase.query(NotesContact.TABLE_NAME, null, null,
            null, null, null, null)
        while (cursor.moveToNext()) {
            var title = cursor.getString(cursor.getColumnIndex(NotesContact.COLUMN_TITLE))
            var description =
                cursor.getString(cursor.getColumnIndex(NotesContact.COLUMN_DESCRIPTION))
            var dayOfWeek = cursor.getString(cursor.getColumnIndex(NotesContact.COLUMN_DAY_OF_WEEK))
            var priority = cursor.getInt(cursor.getColumnIndex(NotesContact.COLUMN_PRIORITY))
            var id = cursor.getInt(cursor.getColumnIndex(NotesContact.ID))

            var note = Note(title, description, dayOfWeek, priority, id)
            listOfNotes.add(note)
        }
        cursor.close()
    }
}