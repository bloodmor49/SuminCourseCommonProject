package com.example.morozovhints.l140_dagger2.ex2_dagger2.data.datasource

import android.util.Log
import javax.inject.Inject

class TestRemoteDataSourceImpl @Inject constructor(): ExampleRemoteDataSource  {
    override fun method() {
        Log.d("TestRemoteDataSource","TestRemoteDataSource")
    }
}