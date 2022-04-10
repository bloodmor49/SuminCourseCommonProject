package com.example.MorozovHints.L102.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.MorozovHints.L102.domain.Item
import com.example.MorozovHints.R
import com.google.android.material.textfield.TextInputLayout

class FragmentStudyMainActivity : AppCompatActivity() {

    private lateinit var buttonLoadItem: Button
    private lateinit var tilID: TextInputLayout
    private lateinit var textInputEditTextId: EditText

    private var itemsContainer: FragmentContainerView? = null

    private var setId = Item.UNDEFINED_ID
    private var landScreen = FragmentSecond.ARG_PARAM1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_study_main)
        initViews()
        buttonItemSet()
        if (!isOnePaneMode()) {
            launchFragment2(FragmentSecond.newInstance(landScreen))
        }

    }

    /**
     * Задаем кнопке функцию передачи данных во фрагмент путем запуска транзакции с фрагментом.
     * Прикрепляем фрагмент через создание экземпляра этого фрагмента.
     */
    private fun buttonItemSet() {
        buttonLoadItem.setOnClickListener {
            setId = textInputEditTextId.text.toString().toInt()
            launchFragment1(FragmentFirst.newInstance(setId))
        }
    }

    /**
     * Инициализируем все видимые части активити.
     */
    private fun initViews() {
        buttonLoadItem = findViewById(R.id.buttonLoadItem)
        tilID = findViewById(R.id.textInputLayoutID)
        textInputEditTextId = findViewById(R.id.TextInputEditTextId)
        itemsContainer = findViewById(R.id.fragment_second_container)
    }

    /**
     * Запуск передачи данных во фрагмент с помощью менеджера путем его перезаписи.
     * На вход задается экземпляр нужного фрагмента.
     */
    private fun launchFragment1(fragment: Fragment) {
        //Саппорт менеджер - менеджер по работе с фрагментами.
        //убираем предыдущий фрагмент из стэка
        supportFragmentManager.popBackStack("add",0)
        //начинаем серию операций с фрагментом.
            supportFragmentManager.beginTransaction()
                //добавляем НАШ ФРАГМЕНТ в контейнер с заменой предыдущего
                .replace(R.id.fragment_first_container, fragment)
                //добавить фрагмент в стэк. Вместо нуля можно указать конкретный фрагмент, чтобы
                //сбрасывались все до него
                .addToBackStack(null)
                //применить изменения
                .commit()

    }
    private fun launchFragment2(fragment: Fragment) {
        //Саппорт менеджер - менеджер по работе с фрагментами.
        //убираем предыдущий фрагмент из стэка
        supportFragmentManager.popBackStack("add",0)
        //начинаем серию операций с фрагментом.
        supportFragmentManager.beginTransaction()
            //добавляем НАШ ФРАГМЕНТ в контейнер с заменой предыдущего
            .replace(R.id.fragment_second_container, fragment)
            //добавить фрагмент в стэк. Вместо нуля можно указать конкретный фрагмент, чтобы
            //сбрасывались все до него
            .addToBackStack("add")
            //применить изменения
            .commit()

    }


    private fun isOnePaneMode(): Boolean {
        return itemsContainer == null
    }
}