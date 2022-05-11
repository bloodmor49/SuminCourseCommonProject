package com.example.morozovhints.L102_fragment_training.data

import android.util.Log
import com.example.morozovhints.L102_fragment_training.domain.Item
import com.example.morozovhints.L102_fragment_training.domain.ItemRepository

object ItemRepositoryImpl: ItemRepository {

    val list = mutableListOf<Item>()

    init{
        for (i in 0..10){
            list.add(Item(i,"item$i"))
        }
        Log.d("ListOfItems", list.toString())
    }
    override fun getItem(id: Int):Item {
        return list[id]
    }

    override fun getItemList(): List<Item> {
        return list
    }
}