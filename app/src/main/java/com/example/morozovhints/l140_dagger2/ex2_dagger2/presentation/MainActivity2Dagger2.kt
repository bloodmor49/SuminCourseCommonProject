package com.example.morozovhints.l140_dagger2.ex2_dagger2.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.morozovhints.R
import com.example.morozovhints.l140_dagger2.ex1.MainActivityDependencies
import com.example.morozovhints.l140_dagger2.ex2_dagger2.L140Application
import javax.inject.Inject

class MainActivity2Dagger2 : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this,viewModelFactory)[ExampleViewModel::class.java]
    }

//    private val component = DaggerApplicationComponent.create()

    // для создания компонента, если к нему привязан контекст. Это позволяет передавать
    //компоненты в модуль. Lazy т.к. контект появляется в onCreate.
    private val component by lazy {

        //решение через билдер
//        DaggerApplicationComponent.builder()
//                //создаем вручную контекст
//              .contextModule(ContextModule(application))
//            .context(this)
//            .currentTime(System.currentTimeMillis())
//            .build()

        //решение через фактори
//        DaggerApplicationComponent.factory().create(application,System.currentTimeMillis())

        //получаем его у application т.е. глобальный синглтон. Главный scope!
        (application as L140Application).component
            .activityComponentFactory()
            .create("MY_ID2","MY_NAME2")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dependencies2)

        component.inject(this)
        viewModel.method()

    }

    private fun initEx1Activity() {
        val mainActivityDependencies = MainActivityDependencies()
        val keyboard = mainActivityDependencies.keyboard.toString()
        Log.d("dagger_init", keyboard)
        val monitor = mainActivityDependencies.monitor.toString()
        Log.d("dagger_init", monitor)
        val mouse = mainActivityDependencies.mouse.toString()
        Log.d("dagger_init", mouse)


        val computer = mainActivityDependencies.computer.toString()
        Log.d("dagger_init", computer)
    }
}