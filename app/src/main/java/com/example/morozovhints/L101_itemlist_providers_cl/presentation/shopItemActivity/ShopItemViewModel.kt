package com.example.morozovhints.L101_itemlist_providers_cl.presentation.shopItemActivity

import androidx.lifecycle.*
import com.example.morozovhints.L101_itemlist_providers_cl.domain.usecases.AddShopItemUseCase
import com.example.morozovhints.L101_itemlist_providers_cl.domain.usecases.EditShopItemUseCase
import com.example.morozovhints.L101_itemlist_providers_cl.domain.usecases.GetShopItemUseCase
import com.example.morozovhints.L101_itemlist_providers_cl.domain.entities.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel экрана настройки элементов списка. (MVVM)
 * Вся работа по загрузке элементов и их обработке происходит ЗДЕСЬ.
 * Активити ТУПО мониторит ОТСЮДА значения через livedata.
 */
class ShopItemViewModel @Inject constructor(
    private val getShopItemUseCase : GetShopItemUseCase,
    private val editShopItemUseCase : EditShopItemUseCase,
    private val addShopItemUseCase : AddShopItemUseCase,
) : ViewModel() {

//    вместо создания скоупа вручную можно использовать viewModelScope
//    val scope = CoroutineScope(Dispatchers.IO)
//        он не требует очистки

    //_ нужно для того, чтобы был доступ из ViewModel, но не из activity, т.к. та не должна задавать
    //значения, а только считывать их. Короче это инфиксная нотация.
    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(shopItemId)
            _shopItem.value = item
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        viewModelScope.launch {
            val name = parseName(inputName)
            val count = parseCount(inputCount)
            val fieldsValid = validateInput(name, count)
            if (fieldsValid) {
                val shopItem = ShopItem(name = name, count = count, enabled = true)
                addShopItemUseCase.addShopItem(shopItem)
                finishWork()
            }
        }

    }

    fun editShopItem(inputName: String?, inputCount: String) {
        viewModelScope.launch {
            val name = parseName(inputName)
            val count = parseCount(inputCount)
            val fieldsValid = validateInput(name, count)
            if (fieldsValid) {
                _shopItem.value?.let {
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editShopItem(item)
                    finishWork()
                }
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

//    override fun onCleared() {
//        super.onCleared()
//        scope.cancel()
//    }
}