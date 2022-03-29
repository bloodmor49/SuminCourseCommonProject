package com.example.MorozovHints.L83.screens.employers

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.MorozovHints.L83.api.ApiFactory
import com.example.MorozovHints.L83.data.AppDataBase
import com.example.MorozovHints.L83.pojo.Employer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EmployerViewModel(application: Application) : AndroidViewModel(application) {

    private var db = AppDataBase.getInstance(application)
    private var employers = db.employersDao().getAllEmployers()
    private var errors = MutableLiveData<Throwable?>()

    fun getEmployers(): LiveData<MutableList<Employer>> = employers

    fun insertEmployers(employers: MutableList<Employer>?) {
        InsertEmployersTask().execute(employers)
    }

    @SuppressLint("StaticFieldLeak")
    inner class InsertEmployersTask : AsyncTask<List<Employer>, Void, Void>() {
        override fun doInBackground(vararg params: List<Employer>?): Void? {
            if (params.isNotEmpty()) {
                params[0]?.let { db.employersDao().insertEmployer(params[0]) }
            }
            return null
        }
    }

    private fun deleteEmployers() {
        DeleteAllEmployersTask().execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class DeleteAllEmployersTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            db.employersDao().deleteAllEmployers()
            return null
        }
    }

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
            .subscribe({
                deleteEmployers()
                insertEmployers(it.response)
            },
                { errors.value = it})
        compositeDisposable.add(disposable)
    }

    fun getErrors(): MutableLiveData<Throwable?> = errors

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun clearErrors(){
        errors.value = null
    }
}