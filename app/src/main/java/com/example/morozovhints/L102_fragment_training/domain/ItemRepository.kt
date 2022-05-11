package com.example.morozovhints.L102_fragment_training.domain

interface ItemRepository {
    fun getItem(id:Int):Item
    fun getItemList():List<Item>
}