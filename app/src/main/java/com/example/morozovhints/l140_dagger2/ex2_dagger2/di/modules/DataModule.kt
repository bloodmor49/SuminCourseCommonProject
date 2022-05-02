package com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules

import com.example.morozovhints.l140_dagger2.ex2_dagger2.data.datasource.*
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.ApplicationScope
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.qualifiers.TestQualifier
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
//class DataModule() {

    //А есть способ проще провайда. Можно взять Binds. Это лучше.
    //Даггер не будет создавать экземпляр класса, а лишь увидит, что данны интерфейс реализуется
    //следующим классом. Всё это абстрактно.
    @ApplicationScope
    @Binds
    fun bindLocalDataSource(impl: ExampleLocalDataSourceImpl): ExampleLocalDataSource


    @ApplicationScope
    @Binds
    fun bindRemoteDataSource(impl: ExampleRemoteDataSourceImpl): ExampleRemoteDataSource

    @TestQualifier
    @ApplicationScope
    @Binds
    fun bindTestRemoteDataSource(impl: TestRemoteDataSourceImpl): ExampleRemoteDataSource


    //Не самая удачная реализация. Принимает реализацию интерфейса и её возвращает.
    //Т.е. мы указываем на это даггеру. Вручную. ^^^^^
//    @Provides
//    fun provideLocalDataSource(impl: ExampleLocalDataSourceImpl): ExampleLocalDataSource {
//        return impl
//    }
//
//    @Provides
//    fun provideRemoteDataSource(impl: ExampleRemoteDataSourceImpl): ExampleRemoteDataSource {
//        return impl
//    }


}