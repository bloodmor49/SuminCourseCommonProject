package com.example.morozovhints.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.L01.L1MainColorSpinnerActivity
import com.example.morozovhints.L02.L2MainCreateMessageActivity
import com.example.morozovhints.L03.L3MainTimerActivity
import com.example.morozovhints.L04.L4MainLoginActivity
import com.example.morozovhints.L05.L5ShopMainActivity
import com.example.morozovhints.L06.L6JSONProjectActivity
import com.example.morozovhints.L071.L7DataStoreMainActivity
import com.example.morozovhints.L072.ViewsMainActivity
import com.example.morozovhints.L081.EmployersMainActivity
import com.example.morozovhints.L082.screens.employers.EmployerListActivity
import com.example.morozovhints.L091.ListOfUsersMainActivity
import com.example.morozovhints.L101_itemlist_pg.presentation.mainActivity.ShopListMainActivity
import com.example.morozovhints.L102.presentation.FragmentStudyMainActivity
import com.example.morozovhints.l111.MainActivityDBinTr
import com.example.morozovhints.R
import com.example.morozovhints.l110.presentation.MainActivityGame
import com.example.morozovhints.l120.MainActivityAsync
import com.example.morozovhints.l130.MainActivityService

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

    //TODO() - пока что переход по активити сделан кастыльно, каюсь, мне стыдно.
    //Обещаю, что потом изменю после прохождения курсов на более адекватную логику.

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
                com.example.morozovhints.L083.VIEW.screens.EmployerListActivity::class.java))
            11 -> startActivity(Intent(this, ListOfUsersMainActivity::class.java))
            12 -> startActivity(Intent(this, ShopListMainActivity::class.java))
            13 -> startActivity(Intent(this, FragmentStudyMainActivity::class.java))
            14 -> startActivity(Intent(this, MainActivityGame::class.java))
            15 -> startActivity(Intent(this, MainActivityDBinTr::class.java))
            16 -> startActivity(Intent(this, MainActivityAsync::class.java))
            17 -> startActivity(Intent(this, MainActivityService::class.java))
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
        listOfLessons.add(LessonInfo("L7.1",
            "Хранение данных (Shared References) - Brain Trainer"))
        listOfLessons.add(LessonInfo("L7.2",
            "RecyclerView(ViewHolder, CardView, Adapter), " +
                    "DB SQLite (Contact,DBHelper,Add,Remove) - Приложение заметок c SQLite"))
        listOfLessons.add(LessonInfo("L8.1",
            "Json->Gson->Retrofit2->RXJAVA3 - Список сотрудников"))
        listOfLessons.add(LessonInfo("L8.2",
            "MVP - Список сотрудников"))
        listOfLessons.add(LessonInfo("L8.3",
            "MVVM - Список сотрудников (+ ROOM, LIVEDATA)"))
        listOfLessons.add(LessonInfo("L9.1",
            "Firebase FireCloud (загрузка,выгрузка,observable) - список пользователей"))
        listOfLessons.add(LessonInfo("L10.1",
            "Clean Architecture - Data,Domain,Presentation, " +
                    "RecyclerView Advanced (DiffUtil, ListAdapter, Pool), Fragments, MVVM - список товаров с firebase"))
        listOfLessons.add(LessonInfo("L10.2",
            "Fragments simple - тренировка."))
        listOfLessons.add(LessonInfo("L11.0",
            "Android JETPACK: ViewBinding, Fragments Advanced," +
                    "dataBinding, JPNav,Bin Adapters, lazy, Parceble, Serialize," +
                    "safeArgs - мат. игра"))
        listOfLessons.add(LessonInfo("L11.1", "Android JETPACK Navigation - тренировка."))
        listOfLessons.add(LessonInfo("L12.0",
            "Многопоточноесть - Callback, AsyncTask,Handler,Looper,Coroutines,Scope."))
        listOfLessons.add(LessonInfo("L13.0",
            "Сервисы - Service."))
    }

    fun testZoneActivity(view: View) {
        startActivity(Intent(this, TestZoneActivity::class.java))
    }


}

