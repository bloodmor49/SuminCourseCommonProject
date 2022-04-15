package com.example.morozovhints.L082.screens.employers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.L082.adapters.EmployerAdapter
import com.example.morozovhints.L082.pojo.Employer
import com.example.morozovhints.R

//https://gitlab.65apps.com/65gb/static/raw/master/testTask.json - Список сотрудников

/**
 * MVP - View часть, взаимодействует с [EmployerListPresenter].
 * Отвечает только за отображение данных. Отправляет запросы на получение данных в Presenter.
 */

class EmployerListActivity : AppCompatActivity(), EmployersListView {

    private lateinit var employerAdapter: EmployerAdapter
    private lateinit var recyclerViewEmployer: RecyclerView
    private lateinit var employerListPresenter: EmployerListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employers_list_activity)

        // Задаем Presenter-у связь с активностью
        employerListPresenter = EmployerListPresenter(this)

        // Находим recyclerView,
        // создаем адаптер,
        // размещаем на экране по layoutManager,
        // задаем ему адаптер
        recyclerViewEmployer = findViewById(R.id.recyclerViewEmployersMVC)
        employerAdapter = EmployerAdapter()
        recyclerViewEmployer.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewEmployer.adapter = employerAdapter

        //вызов presenter - загрузка данных (Запрос к presenter)
        employerListPresenter.loadData()

    }

    //Отображение данных (наследуется интерфейс)
    override fun showData(employers: MutableList<Employer>){
        employerAdapter.listOfEmployer = employers
    }

    //Отображение ошибки (наследуется интерфейс)
    override fun showError(){
        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
    }

    //При уничтожении актвиности - завершает поток presenter-а (запрос к Presenter)
    override fun onDestroy() {
        employerListPresenter.disposeDisposable()
        super.onDestroy()
    }
}
