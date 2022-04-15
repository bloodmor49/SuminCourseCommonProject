package com.example.morozovhints.L102.domain

class GetItemListUseCase(private val repository: ItemRepository) {
    fun getItemList(): List<Item> {
        return repository.getItemList()
    }
}