package com.example.morozovhints.l130

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.morozovhints.R
import com.example.morozovhints.databinding.ActivityMainServiceBinding

//
// Проблемы обычных сервисов (симпл,foreground):
// 1) они работают (onStartCommand) в главном потоке
// 2) остановка вручную (stopSelf,stopService)
// 3) при запуске несколько раз будет куча сервисов параллельно. (нужно контроллировать вручную)
//
// Решение - IntentService. Он устарел, но в нем решены все эти три проблемы.
//
// JobService,JobSheduler - сервисы, которые выполняются при определенных условиях.
// Например скачать что - то только при подключении к wifi либо при зарядке телефона.
//
class MainActivityService : AppCompatActivity() {

    private val viewbinding by lazy {
        ActivityMainServiceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewbinding.root)
        viewbinding.simpleService.setOnClickListener {
            Toast.makeText(this,"Click",Toast.LENGTH_LONG).show()
            startService(MyService.newIntent(this,25))
        }
//      Останов сервиса СНАРУЖИ.
        viewbinding.simpleService.setOnLongClickListener {
            stopService(MyService.newIntent(this,0))
        }
        viewbinding.foregroundService.setOnClickListener {
//      Мы обещаем, что покажем уведомление пользователю. Это делается внутри сервиса.
            ContextCompat.startForegroundService(this,MyForeGroundService.newIntent(this))
        }
        viewbinding.foregroundService.setOnLongClickListener {
            Toast.makeText(this,"Stop service",Toast.LENGTH_LONG).show()
            stopService(MyForeGroundService.newIntent(this))
        }
        viewbinding.intentService.setOnClickListener {
            ContextCompat.startForegroundService(this,MyIntentService.newIntent(this))
        }
    }



}