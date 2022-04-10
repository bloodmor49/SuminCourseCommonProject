package com.example.MorozovHints.L102.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.MorozovHints.L102.data.ItemRepositoryImpl
import com.example.MorozovHints.L102.domain.GetItemUseCase
import com.example.MorozovHints.L102.domain.Item
import com.example.MorozovHints.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFirst.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFirst : Fragment() {

    private var param1: Int? = null
    private var textViewNoteInfo: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        getParamsFromOuter()
        setTextToTextView()

    }

    /**
     * Задаем текст вью значение параметра по id.
     */
    private fun setTextToTextView() {
        val repository = ItemRepositoryImpl
        val getItemUseCase = GetItemUseCase(repository)
        val textedItem = param1?.let { getItemUseCase.getItem(it) }
        textViewNoteInfo?.text = textedItem?.name
    }

    /**
     * Получение параметров из внешних источников с заданным тегом.
     */
    private fun getParamsFromOuter() {
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1, -1)
        }
    }

    /**
     * Инициализация визуальныйх view элементов.
     */
    private fun initView(view: View) {
        textViewNoteInfo = view.findViewById(R.id.textViewNoteInfo)
    }

    companion object {

        private const val ARG_PARAM1 = "param1"

        //создание экземпляра фрагмента с передачей аргументов во фрагмент. Единственный!
        //и передаем в него аргументы, которые задаем при вызове объекта
        fun newInstance(param1: Int) =
            FragmentFirst().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}

