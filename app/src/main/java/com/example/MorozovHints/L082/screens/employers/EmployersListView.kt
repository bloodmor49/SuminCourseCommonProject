package com.example.MorozovHints.L082.screens.employers

import com.example.MorozovHints.L082.pojo.Employer

/**
 * Интерфейс для EmployerListActivity.
 * Перезаписывается в activity для отображения запросов через Presenter.
 * Фактически представляет собой список методов для работы с мейн активити.
 * Методы переопределяются под необходимые.
 */
interface EmployersListView {
    fun showData(employers: MutableList<Employer>){}
    fun showError(){}
}