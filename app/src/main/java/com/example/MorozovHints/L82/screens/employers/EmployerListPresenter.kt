package com.example.MorozovHints.L82.screens.employers

import com.example.MorozovHints.L82.api.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * MVP - Presenter часть. Получает задание от Activity (View) отобразить данные.
 * Отправляет в Model запрос на получение данных. В данном случае model ApiFactory,Service.
 * Presenter не знает как отображать данные, а лишь передает их в View.
 * Наследует View методы из интерфейса ListView
 */
class EmployerListPresenter(private val view: EmployersListView) {

    private lateinit var disposable: Disposable
    private lateinit var compositeDisposable: CompositeDisposable

    fun loadData() {
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
            .subscribe({ it.response?.let { it1 -> view.showData(it1) } },
                {view.showError() })
        compositeDisposable.add(disposable)
    }

    fun disposeDisposable() {
        let { compositeDisposable.dispose() }
    }
}