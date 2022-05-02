package com.example.morozovhints.l140_dagger2.ex2_dagger2.data.datasource

import com.example.morozovhints.l140_dagger2.ex2_dagger2.data.network.ExampleApiService
import javax.inject.Inject

class ExampleRemoteDataSourceImpl @Inject constructor(
    private val apiService: ExampleApiService
) : ExampleRemoteDataSource {

    override fun method() {
        apiService.method()
    }
}