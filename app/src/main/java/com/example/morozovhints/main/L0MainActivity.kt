package com.example.morozovhints.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.L01_spinner_xml_training.L1MainColorSpinnerActivity
import com.example.morozovhints.L02_intents_training.L2MainCreateMessageActivity
import com.example.morozovhints.L03_rotate_save_info.L3MainTimerActivity
import com.example.morozovhints.L04_xml_training.L4MainLoginActivity
import com.example.morozovhints.L05_xml_training.L5ShopMainActivity
import com.example.morozovhints.L06_json_download.L6JSONProjectActivity
import com.example.morozovhints.L071_shared_preferences.L7DataStoreMainActivity
import com.example.morozovhints.L072_sqlite_room.ViewsMainActivity
import com.example.morozovhints.L081_training_jun_task_retrofit_json.EmployersMainActivity
import com.example.morozovhints.L082_MVP.screens.employers.EmployerListActivity
import com.example.morozovhints.L091_firebase.ListOfUsersMainActivity
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.mainActivity.ShopListMainActivity
import com.example.morozovhints.L102_fragment_training.presentation.FragmentStudyMainActivity
import com.example.morozovhints.l111_jetpack_training.MainActivityDBinTr
import com.example.morozovhints.R
import com.example.morozovhints.l110_jetpack.presentation.MainActivityGame
import com.example.morozovhints.l120_async_methods.MainActivityAsync
import com.example.morozovhints.l130_services.MainActivityService
import com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation.MainActivityDagger2
import com.example.morozovhints.l150_broadcast_receiver.MainActivityBroadcast
import com.example.morozovhints.l160_content_providers_contacts.ContactsMainActivity

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
                com.example.morozovhints.L083_retrofit_gson.VIEW.screens.EmployerListActivity::class.java))
            11 -> startActivity(Intent(this, ListOfUsersMainActivity::class.java))
            12 -> startActivity(Intent(this, ShopListMainActivity::class.java))
            13 -> startActivity(Intent(this, FragmentStudyMainActivity::class.java))
            14 -> startActivity(Intent(this, MainActivityGame::class.java))
            15 -> startActivity(Intent(this, MainActivityDBinTr::class.java))
            16 -> startActivity(Intent(this, MainActivityAsync::class.java))
            17 -> startActivity(Intent(this, MainActivityService::class.java))
            18 -> startActivity(Intent(this, MainActivityDagger2::class.java))
            19 -> startActivity(Intent(this, MainActivityBroadcast::class.java))
            20 -> startActivity(Intent(this, ContactsMainActivity::class.java))
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
                    "RecyclerView Advanced (DiffUtil, ListAdapter, Pool), " +
                    "Fragments, MVVM, contentProvider - список товаров с firebase"))
        listOfLessons.add(LessonInfo("L10.2",
            "Fragments simple - тренировка."))
        listOfLessons.add(LessonInfo("L11.0",
            "Android JETPACK: ViewBinding, Fragments Advanced," +
                    "dataBinding, JPNav,Bin Adapters, lazy, Parceble, Serialize," +
                    "safeArgs - мат. игра"))
        listOfLessons.add(LessonInfo("L11.1", "Android JETPACK Navigation - тренировка."))
        listOfLessons.add(LessonInfo("L12.0",
            "Многопоточноесть - Callback, AsyncTask,Handler,Looper,Coroutines Basics,Scope."))
        listOfLessons.add(LessonInfo("L13.0",
            "Сервисы - Services + Android JetPack (WorkManager)."))
        listOfLessons.add(LessonInfo("L14.0",
            "Инъекция зависимостей - Dagger2."))
        listOfLessons.add(LessonInfo("L15.0",
            "BroadCast receivers"))
        listOfLessons.add(LessonInfo("L16.0",
            "Content Provider - получение контактов из телефона."))
    }

    fun testZoneActivity(view: View) {
        startActivity(Intent(this, TestZoneActivity::class.java))
    }


}

