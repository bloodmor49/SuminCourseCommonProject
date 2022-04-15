package com.example.morozovhints.L102.domain

interface ItemRepository {
    fun getItem(id:Int):Item
    fun getItemList():List<Item>
}