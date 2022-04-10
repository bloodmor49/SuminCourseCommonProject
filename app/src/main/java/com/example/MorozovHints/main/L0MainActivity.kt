package com.example.MorozovHints.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L01.L1MainColorSpinnerActivity
import com.example.MorozovHints.L02.L2MainCreateMessageActivity
import com.example.MorozovHints.L03.L3MainTimerActivity
import com.example.MorozovHints.L04.L4MainLoginActivity
import com.example.MorozovHints.L05.L5ShopMainActivity
import com.example.MorozovHints.L06.L6JSONProjectActivity
import com.example.MorozovHints.L071.L7DataStoreMainActivity
import com.example.MorozovHints.L072.ViewsMainActivity
import com.example.MorozovHints.L081.EmployersMainActivity
import com.example.MorozovHints.L082.screens.employers.EmployerListActivity
import com.example.MorozovHints.L091.ListOfUsersMainActivity
import com.example.MorozovHints.L101.presentation.mainActivity.ShopListMainActivity
import com.example.MorozovHints.L102.presentation.FragmentStudyMainActivity
import com.example.MorozovHints.R

class L0MainActivity : AppCompatActivity() {

    private var listOfLessons = mutableListOf<LessonInfo>()

    private lateinit var recyclerViewLessons: RecyclerView
    private lateinit var lessonsAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler)
        actionBar?.show()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerViewLessons = findViewById(R.id.RecyclerViewLessons)
        addLessons(listOfLessons)
        lessonsAdapter = MainRecyclerAdapter(listOfLessons)
        recyclerViewLessons.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewLessons.adapter = lessonsAdapter

        lessonsAdapter.onLessonClickListener = object : MainRecyclerAdapter.OnLessonClickListener {
            override fun onLessonClick(position: Int) {
                startActivityById(position)
            }
        }
    }

    //TODO() - пока что переход по активити сделан кастыльно, потом изменю после прохождения курсов

    private fun startActivityById(id: Int) {
        when (id) {
            0 -> startActivity(Intent(this, L1MainColorSpinnerActivity::class.java))
            1 -> startActivity(Intent(this, L2MainCreateMessageActivity::class.java))
            2 -> startActivity(Intent(this, L3MainTimerActivity::class.java))
            3 -> startActivity(Intent(this, L4MainLoginActivity::class.java))
            4 -> startActivity(Intent(this, L5ShopMainActivity::class.java))
            5 -> startActivity(Intent(this, L6JSONProjectActivity::class.java))
            6 -> startActivity(Intent(this, L7DataStoreMainActivity::class.java))
            7 -> startActivity(Intent(this, ViewsMainActivity::class.java))
            8 -> startActivity(Intent(this, EmployersMainActivity::class.java))
            9 -> startActivity(Intent(this, EmployerListActivity::class.java))
            10 -> startActivity(Intent(this,
                com.example.MorozovHints.L083.VIEW.screens.EmployerListActivity::class.java))
            11 -> startActivity(Intent(this, ListOfUsersMainActivity::class.java))
            12 -> startActivity(Intent(this, ShopListMainActivity::class.java))
            13 -> startActivity(Intent(this, FragmentStudyMainActivity::class.java))
        }
    }

    private fun addLessons(listOfLessons: MutableList<LessonInfo>) {
        listOfLessons.add(LessonInfo("L1",
            "XML, кнопки, методы - спиннер цвета"))
        listOfLessons.add(LessonInfo("L2",
            "2 активности, намерения, действия - отправка сообщений"))
        listOfLessons.add(LessonInfo("L3",
            "Жизенный цикл активностей (видимость, рождение, смерть), " +
                    "HANDLER (Runnable, run) - таймер"))
        listOfLessons.add(LessonInfo("L4",
            "Работа с XML - LinearLayout,RadioGroup,CheckBox,ScrollView, " +
                    "а также StringBuilder и View.Visible  - Заказ кофе"))
        listOfLessons.add(LessonInfo("L5",
            "Работа с Toast, listView, слушателем SetOnItemClickListener, " +
                    "адаптером (string -> listView), SeekBar - Магазин кружен"))
        listOfLessons.add(LessonInfo("L6",
            "Загрузка данных из интернета, JSON, LogCat - Погода в городе"))
        listOfLessons.add(LessonInfo("L71",
            "Хранение данных (Shared References) - Brain Trainer"))
        listOfLessons.add(LessonInfo("L72",
            "RecyclerView(ViewHolder, CardView, Adapter), " +
                    "DB SQLite (Contact,DBHelper,Add,Remove) - Приложение заметок c SQLite"))
        listOfLessons.add(LessonInfo("L81",
            "Json->Gson->Retrofit2->RXJAVA3 - Список сотрудников"))
        listOfLessons.add(LessonInfo("L82",
            "MVP - Список сотрудников"))
        listOfLessons.add(LessonInfo("L83",
            "MVVM - Список сотрудников (+ ROOM, LIVEDATA)"))
        listOfLessons.add(LessonInfo("L91",
            "Firebase FireCloud (загрузка,выгрузка,observable) - список пользователей"))
        listOfLessons.add(LessonInfo("L101",
            "Clean Architecture - Data,Domain,Presentation, " +
                    "RecyclerView Advanced (DiffUtil, ListAdapter, Pool), Fragments, MVVM - список товаров с firebase"))
        listOfLessons.add(LessonInfo("L102",
            "Fragments simple, тренировка, простая активити и простая работа с фрагментом"))
    }

    fun testZoneActivity(view: View) {
        startActivity(Intent(this, TestZoneActivity::class.java))
    }


}

