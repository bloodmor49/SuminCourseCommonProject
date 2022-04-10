package com.example.MorozovHints.L102.domain

import com.example.MorozovHints.L102.data.ItemRepositoryImpl

class GetItemUseCase(private val repository: ItemRepository) {
    fun getItem(id:Int):Item{
        return repository.getItem(id)
    }
}