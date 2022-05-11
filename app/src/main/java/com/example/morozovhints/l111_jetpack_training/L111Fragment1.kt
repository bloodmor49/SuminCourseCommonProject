package com.example.morozovhints.l111_jetpack_training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.morozovhints.databinding.FragmentL1111Binding

class L111Fragment1 : Fragment() {

    private lateinit var data: L111Data
    var stringData = "STRING_TO_FRAGMENT2"
    var intData = 20

    //Создаем viewBinding
    private var _viewBinding: FragmentL1111Binding? = null
    private val viewBinding: FragmentL1111Binding
        get() = _viewBinding ?: throw RuntimeException("FragmentChooseLevelBinding = null ")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //привязываем элементы XML к ViewBinding
        _viewBinding = FragmentL1111Binding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data = L111Data()

        //задаем кнопку на переход ко второму фрагменту
        viewBinding.buttonToF2.setOnClickListener {
            launchGameFragment2(stringData)
        }
    }

    private fun launchGameFragment2(stringData: String) {
        //с помощью нав контроллера определяем куда идти и что передать
        findNavController().navigate(L111Fragment1Directions.actionL111Fragment1ToL111Fragment2(
            stringData, intData, data))
    }
}