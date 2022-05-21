package com.example.morozovhints.l120_async_methods

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val parentJob = Job()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.i(LOG_TAG, "exception catched: $throwable")
    }

    private val corutineScope = CoroutineScope(Dispatchers.Main + parentJob + exceptionHandler)

    suspend fun method1() {

        val childJob1 = corutineScope.launch {
            delay(3000)
            Log.i(LOG_TAG, "first coroutine finished")
        }
        val childJob2 = corutineScope.launch {
            delay(2000)
//            childJob1.cancel()
            Log.i(LOG_TAG, "second coroutine finished")
        }
        val childJob3 = corutineScope.launch {
            delay(1000)
            error()
            Log.i(LOG_TAG, "third coroutine finished")
        }
        //        thread {
//            Thread.sleep(1000)
//            parentJob.cancel()
//            Log.i(LOG_TAG,"Parent job is active: ${parentJob.isActive}")
//        }
//        Log.i(LOG_TAG,parentJob.children.contains(childJob1).toString())
//        Log.i(LOG_TAG,parentJob.children.contains(childJob2).toString())
//    }
    }

    fun method2() {
        val jobCounter = viewModelScope.launch(Dispatchers.Default) {
            Log.i(LOG_TAG, "Started")
            var counter = 0
            val before = System.currentTimeMillis()
            for (i in 0..100_000_000) {
                for (j in 0..100) {
                    if (isActive) counter++
                    else throw CancellationException()
                }
            }
            Log.i(LOG_TAG, "Finished: ${System.currentTimeMillis() - before}")
        }
        jobCounter.invokeOnCompletion {
            Log.i(LOG_TAG, "Coroutine cancelled: $it")
        }
        viewModelScope.launch {
            delay(3000)
            jobCounter.cancel()
        }
    }

    private fun error() {
        throw RuntimeException()
    }

    override fun onCleared() {
        super.onCleared()
        corutineScope.cancel()
    }

    companion object {
        const val LOG_TAG = "MainViewModel"
    }
}