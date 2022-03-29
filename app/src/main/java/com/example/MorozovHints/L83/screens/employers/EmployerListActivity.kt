package com.example.MorozovHints.L83.screens.employers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L83.adapters.EmployerAdapter
import com.example.MorozovHints.R

//https://gitlab.65apps.com/65gb/static/raw/master/testTask.json - Список сотрудников

/**
 * MVP - View часть, взаимодействует с [EmployerListPresenter].
 * Отвечает только за отображение данных. Отправляет запросы на получение данных в Presenter.
 */

class EmployerListActivity : AppCompatActivity() {

    private lateinit var employerAdapter: EmployerAdapter
    private lateinit var recyclerViewEmployer: RecyclerView
    private lateinit var viewModel: EmployerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employers_list_activity_mvvm)

        // Находим recyclerView,
        // создаем адаптер,
        // размещаем на экране по layoutManager,
        // задаем ему адаптер
        recyclerViewEmployer = findViewById(R.id.recyclerViewEmployersMVC)
        employerAdapter = EmployerAdapter()
        recyclerViewEmployer.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewEmployer.adapter = employerAdapter

        viewModel = ViewModelProvider(this)[EmployerViewModel::class.java]

        viewModel.getEmployers().observe(this, Observer {
            employerAdapter.listOfEmployer = it
            for (employer in it) {
                var specialties = employer.specialty
                if (specialties != null) {
                    for (specialty in specialties){
                        Log.i("Specialty", "$specialty  ${specialty!!.name!!}")
                    }
                }
            }
        })

        viewModel.loadData()
        viewModel.getErrors()
            .observe(this, Observer {
                Toast.makeText(this, it?.message, Toast.LENGTH_LONG).show()
                viewModel.clearErrors()
            })


    }
}
