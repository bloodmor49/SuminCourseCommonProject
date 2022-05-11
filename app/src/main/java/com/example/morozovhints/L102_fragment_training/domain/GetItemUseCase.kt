package com.example.morozovhints.L102_fragment_training.domain

class GetItemUseCase(private val repository: ItemRepository) {
    fun getItem(id:Int):Item{
        return repository.getItem(id)
    }
}