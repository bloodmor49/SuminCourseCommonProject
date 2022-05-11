package com.example.morozovhints.L102_fragment_training.domain

class GetItemListUseCase(private val repository: ItemRepository) {
    fun getItemList(): List<Item> {
        return repository.getItemList()
    }
}