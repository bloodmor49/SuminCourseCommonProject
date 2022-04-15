package com.example.morozovhints.L083.VIEW.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.L083.VIEW.adapters.EmployerAdapter
import com.example.morozovhints.L083.VIEWMODEL.EmployerViewModel
import com.example.morozovhints.R

//https://gitlab.65apps.com/65gb/static/raw/master/testTask.json - Список сотрудников

/**
 * MVVM - View часть.
 * Отвечает только за отображение данных. Отправляет запросы на получение данных из [EmployerViewModel]
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
        setUpRecyclerView()
        setUpViewModel()


    }

    private fun setUpViewModel() {
        //создаем viewModel из класса EmployerViewModel
        viewModel = ViewModelProvider(this)[EmployerViewModel::class.java]
        //выбираем данные для просматривания livedata
        viewModel.getEmployers().observe(this, Observer {
            employerAdapter.listOfEmployer = it
            for (employer in it) {
                var specialties = employer.specialty
                if (specialties != null) {
                    for (specialty in specialties) {
                        Log.i("Specialty", "$specialty  ${specialty!!.name!!}")
                    }
                }
            }
        })

        //загружаем данные
        viewModel.loadData()
        //просматриваем ошибки
        viewModel.getErrors().observe(this, Observer {
            Toast.makeText(this, it?.message, Toast.LENGTH_LONG).show()
            viewModel.clearErrors()
        })
    }

    private fun setUpRecyclerView() {
        recyclerViewEmployer = findViewById(R.id.recyclerViewEmployersMVC)
        employerAdapter = EmployerAdapter()
        recyclerViewEmployer.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewEmployer.adapter = employerAdapter
    }
}
