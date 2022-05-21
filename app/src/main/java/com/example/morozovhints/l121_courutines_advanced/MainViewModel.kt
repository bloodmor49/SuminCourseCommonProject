package com.example.morozovhints.l121_courutines_advanced

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.math.BigInteger

class MainViewModel : ViewModel() {

    private val coroutineScope =
        CoroutineScope(Dispatchers.Main + CoroutineName("MyCoroutineScope"))


    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state


    fun calculate(value: String?) {
        _state.value = Progress

        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }

        coroutineScope.launch {
            val number = value.toLong()
            withContext(Dispatchers.Default) {
                val result = factorial(number)
                withContext(Dispatchers.Main) {
                    delay(1000)
                    _state.value = Result(result)
                    Log.i("myCoroutine", "$coroutineContext")
                }
            }
        }
    }

    private fun factorial(n: Long): String {
        var result = BigInteger.ONE
        for (i in 1..n) result = result.multiply(BigInteger.valueOf(i))
        return result.toString()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}