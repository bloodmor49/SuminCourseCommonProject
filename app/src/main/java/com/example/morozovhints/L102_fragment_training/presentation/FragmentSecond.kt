package com.example.morozovhints.L102_fragment_training.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.morozovhints.L102_fragment_training.data.ItemRepositoryImpl
import com.example.morozovhints.L102_fragment_training.domain.GetItemListUseCase
import com.example.morozovhints.R

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSecond.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSecond : Fragment() {
    private var param1: String? = null
    private lateinit var textViewItems : TextView
    private lateinit var textViewLandScreen : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewItems = view.findViewById(R.id.textViewItems)
        textViewLandScreen = view.findViewById(R.id.textViewLandScreen)
        textViewLandScreen.text = param1

        var repository = ItemRepositoryImpl
        var getItemListUseCase = GetItemListUseCase(repository)
        val listOfItems = getItemListUseCase.getItemList()
        textViewItems.text = listOfItems.toString()

    }

    companion object {
        const val ARG_PARAM1 = "LANDSCREEN"

        fun newInstance(param1: String) =
            FragmentSecond().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}