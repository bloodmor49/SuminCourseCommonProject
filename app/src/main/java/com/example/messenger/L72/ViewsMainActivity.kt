package com.example.messenger.L72

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.L72.ViewsMainActivity.Notes.listOfNotes
import com.example.messenger.L72.RoomDB.Note
import com.example.messenger.L72.RoomDB.NotesDatabase
import com.example.messenger.L72.recyclerViewAdapter.NotesAdapter
import com.example.messenger.R

open class ViewsMainActivity : AppCompatActivity() {

    object Notes {
        var listOfNotes = mutableListOf<Note>()
    }

    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var adapter: NotesAdapter

    private var database: NotesDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l71_views_main)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)

        //Создаем адаптер
        adapter = NotesAdapter(listOfNotes)
        //Задаем layout
        recyclerViewNotes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //задаем значение адаптеру recyclerView - notes адаптер.
        recyclerViewNotes.adapter = adapter

        //получаем базу данных с помощью синглтона
        database = NotesDatabase.getInstance(this)
        //вызываем метод получить данные из дб.
        getData()

        adapter.onNoteClickListener = object : NotesAdapter.OnNoteClickListener {
            override fun onNoteClick(position: Int) {
                Toast.makeText(this@ViewsMainActivity, "$position", Toast.LENGTH_LONG).show()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onNoteLongClick(position: Int) {
            }
        }

        //Удаление свайпом с помощью callBack (обратная реакция) и анонимного класса SimpleCallback
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
        //ItemTouchHelper - класс для работы с нажатием в recyclerView. Работает с callback.
        var itemTouchHelper = ItemTouchHelper(simpleCallback)
        //привязка свайпа к нашему recyclerView
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes)
    }

    //Добавление новой записки
    fun onClickAddNote(view: View) {
        startActivity(Intent(this, AddNoteActivity::class.java))
    }

    //Удаление записки
    @SuppressLint("NotifyDataSetChanged")
    fun removeNote(position: Int) {
        //берем выбранную записку
        var note = listOfNotes[position]
        //вызываем метод удаления записки из интерфейса DBDAO
        database?.notesDao()?.deleteNote(note)
        //заного получаем данные
        getData()
        //обновляем информацию для адаптера.
        adapter.notifyItemRemoved(position)
    }

    //получение данных из дб
    private fun getData(){
        //выгружаем из ДБ все записки с помощью интерфейса DAO (содержит методы работы)
        var noteFromDB = database?.notesDao()?.getAllNotes()
        //очищаем лист с записками
        listOfNotes.clear()
        //добавляем в лист данные из дб, если они есть.
        if (noteFromDB != null) {
            listOfNotes.addAll(noteFromDB)
        }
    }

}