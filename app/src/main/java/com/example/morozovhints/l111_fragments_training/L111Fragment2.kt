package com.example.morozovhints.l111_fragments_training

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.morozovhints.databinding.FragmentL1112Binding

class L111Fragment2 : Fragment() {

    private val args by navArgs<L111Fragment2Args>()

    //Создаем viewBinding
    private var _viewBinding: FragmentL1112Binding? = null
    private val viewBinding: FragmentL1112Binding
        get() = _viewBinding ?: throw RuntimeException("FragmentChooseLevelBinding = null ")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentL1112Binding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment2",args.toString())
        viewBinding.l111Data = args.l111Data
        viewBinding.TVStringFromF1.text = args.stringArgFromF1
        viewBinding.TVIntFromF1.text = args.intArgFromF1.toString()
    }
}