package com.example.morozovhints.L072_sqlite_room

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.L072_sqlite_room.RoomDB.Note
import com.example.morozovhints.L072_sqlite_room.ViewModel.MainViewModel
import com.example.morozovhints.L072_sqlite_room.recyclerViewAdapter.NotesAdapter
import com.example.morozovhints.R

open class ViewsMainActivity : AppCompatActivity() {

    var listOfNotes = mutableListOf<Note>()

    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l71_views_main)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //Создаем адаптер
        adapter = NotesAdapter(listOfNotes)
        //Задаем layout
        recyclerViewNotes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //задаем значение адаптеру recyclerView - notes адаптер.
        recyclerViewNotes.adapter = adapter

        //вызываем метод получить данные из дб.
        getData()

        //без использования лямбда функций
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
        viewModel.deleteNote(note)
        //заного получаем данные
        getData()
        //обновляем информацию для адаптера.
        adapter.notifyItemRemoved(position)
    }

    //получение данных из дб
    private fun getData() {
        //выгружаем из ДБ все записки с помощью интерфейса DAO (содержит методы работы)
        var noteFromDB = viewModel.getNotes()

        var observer = Observer<List<Note>>{
            listOfNotes.clear()
            listOfNotes.addAll(it)
            adapter.notifyDataSetChanged()
        }

        noteFromDB?.observe(this,observer)

    }

}