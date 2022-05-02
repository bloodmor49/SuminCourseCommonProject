package com.example.morozovhints.l140_dagger2.ex2_dagger2.data.database

import android.content.Context
import android.util.Log
import com.example.morozovhints.R
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Экземпляр базы данных. Инджектим  - теперь даггер может создавать бд.
 */
@ApplicationScope
class ExampleDatabase @Inject constructor(
    private val context: Context,
    private val currentTime: Long
){

    fun method() {
        Log.d(LOG_TAG, "ExampleDatabase ${context.getString(R.string.Download)}: $currentTime")
    }

    companion object {

        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}