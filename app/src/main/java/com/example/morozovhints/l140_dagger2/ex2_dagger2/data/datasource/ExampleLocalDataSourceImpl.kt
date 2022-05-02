package com.example.morozovhints.l140_dagger2.ex2_dagger2.data.datasource

import com.example.morozovhints.l140_dagger2.ex2_dagger2.data.database.ExampleDatabase
import javax.inject.Inject

class ExampleLocalDataSourceImpl @Inject constructor(
    private val database: ExampleDatabase
) : ExampleLocalDataSource {

    override fun method() {
        database.method()
    }
}