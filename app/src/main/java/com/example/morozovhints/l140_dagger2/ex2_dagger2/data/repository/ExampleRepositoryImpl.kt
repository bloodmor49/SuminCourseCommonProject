package com.example.morozovhints.l140_dagger2.ex2_dagger2.data.repository

import com.example.morozovhints.l140_dagger2.ex2_dagger2.data.datasource.ExampleLocalDataSource
import com.example.morozovhints.l140_dagger2.ex2_dagger2.data.datasource.ExampleRemoteDataSource
import com.example.morozovhints.l140_dagger2.ex2_dagger2.data.mapper.ExampleMapper
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.qualifiers.TestQualifier
import com.example.morozovhints.l140_dagger2.ex2_dagger2.domain.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val localDataSource: ExampleLocalDataSource,
    @TestQualifier private val remoteDataSource: ExampleRemoteDataSource,
    private val mapper: ExampleMapper,
) : ExampleRepository {
    override fun method() {
        mapper.map()
        localDataSource.method()
        remoteDataSource.method()
    }
}