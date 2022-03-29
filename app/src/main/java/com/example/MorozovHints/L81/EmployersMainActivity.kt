package com.example.MorozovHints.L81

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L81.adapters.EmployerAdapter
import com.example.MorozovHints.L81.api.ApiFactory
import com.example.MorozovHints.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


//https://gitlab.65apps.com/65gb/static/raw/master/testTask.json - Список сотрудников

class EmployersMainActivity : AppCompatActivity() {

    private lateinit var employerAdapter: EmployerAdapter
    private lateinit var recyclerViewEmployer: RecyclerView
    private lateinit var disposable: Disposable
    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employers_main)

        //Находим recyclerView,
        // создаем адаптер,
        // размещаем на экране по layoutManager,
        // задаем ему адаптер
        recyclerViewEmployer = findViewById(R.id.recyclerViewEmployers)
        employerAdapter = EmployerAdapter()
        recyclerViewEmployer.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewEmployer.adapter = employerAdapter

        //создаем подключение
        var apiFactory = ApiFactory.getInstance()
        //создаем запросы
        var apiService = apiFactory.getApiService()

        //Общий Disposable, содержит все потоки Disposable
        compositeDisposable = CompositeDisposable()

        disposable = apiService.getEmployers()
            //В каком потоке выполняется запрос (загрузка данных)
            .subscribeOn(Schedulers.io())
            //В каком потоке принимаются данные
            .observeOn(AndroidSchedulers.mainThread())
            //При получении данных
            .subscribe({ employerAdapter.listOfEmployer = it.response!! },
                { Toast.makeText(this, "Ошибочка вышла\n${it.message}", Toast.LENGTH_LONG).show() })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        let { compositeDisposable.dispose() }
        super.onDestroy()
    }
}
