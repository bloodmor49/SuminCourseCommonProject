package com.example.morozovhints.l140_dagger2.ex2_dagger2.di.modules

import com.example.morozovhints.l140_dagger2.ex2_dagger2.data.repository.ExampleRepositoryImpl
import com.example.morozovhints.l140_dagger2.ex2_dagger2.domain.ExampleRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    //мы не можем интерфейсы инджектить поэтому при вызове репозитория мы провайдим его реализацию.
    @Provides
    fun provideRepository(impl:ExampleRepositoryImpl):ExampleRepository{
        return impl
    }
}