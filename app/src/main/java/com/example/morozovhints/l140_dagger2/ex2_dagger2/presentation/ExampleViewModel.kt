package com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.qualifiers.IdQualifier
import com.example.morozovhints.l140_dagger2.ex2_dagger2.di.qualifiers.NameQualifier
import com.example.morozovhints.l140_dagger2.ex2_dagger2.domain.ExampleUseCase
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val useCase: ExampleUseCase,
    //через named
//    @Named("id") private val id: String,
//    @Named("name")private val name: String
    //через наши аннотации
    @IdQualifier private val id: String,
    @NameQualifier private val name: String
) : ViewModel() {
    fun method() {
        useCase()
        Log.d("ExampleViewModel","$this $id $name")
    }
}