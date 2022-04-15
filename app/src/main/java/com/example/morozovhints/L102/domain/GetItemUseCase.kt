package com.example.morozovhints.L102.domain

class GetItemUseCase(private val repository: ItemRepository) {
    fun getItem(id:Int):Item{
        return repository.getItem(id)
    }
}