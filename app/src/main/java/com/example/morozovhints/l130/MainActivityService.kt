package com.example.morozovhints.l130

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
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
// В связи с тем, что существуют две условные версии относительно 26, то используется
// JobIntentService - комбинация предыдущих. В зависимости от версии он применяет Интент Сервис
// или ДжобСервис. Однако так невозможно осуществить действия по условиям как jobScheduler.
//
// На данный момент на смену этим сервисам пришёл android jetpack с его WORK MANAGER.
//
class MainActivityService : AppCompatActivity() {

    private var page = 0

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
        viewbinding.jobScheduler.setOnClickListener {
//          Запуск сервиса job -
//          1) даем имя и привязываем к классу сервиса, передаем контекст.
            val componentName = ComponentName(this,MyJobService::class.java)
//          2) создаем информацию о сервисе и ограничения - когда работает, какой ID и так далее.
            val jobInfo = JobInfo.Builder(MyJobService.JOB_ID,componentName)
                //передача данных в сервис. Для sheduler.
//                .setExtras(MyJobService.newBundle(page++))
//             работает при зарядке
                .setRequiresCharging(true)
//             работает при работе вай фай
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//             включается при включении телефона
//                .setPersisted(true)
//             как часто включается.
//                .setPeriodic()
                .build()
//          3) запускаем созданный сервис.
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
//            schedule удаляет предыдущий сервис.
//            jobScheduler.schedule(jobInfo)
//            enqueue же продолжает сервис с точки удаления прошлого сервиса, так как работают очереди.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intent = MyJobService.newIntent(page++)
//              Добавляем сервис в очередь.
                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
            } else {
                val intent = MyIntentServiceForJobLower26.newIntent(this,page++)
                startService(intent)
            }

            //такой сервис будет перезапущен при восстановлении условий его работы.
            //и начинает работать с самого начала.


            viewbinding.jobIntentService.setOnClickListener {
                MyJobIntentService.enqueue(this, page++)
            }

            viewbinding.workManager.setOnClickListener {
                val workManager = WorkManager.getInstance()
                //enqueue - все воркеры безымянны и начинают выполнение друг за другом.
                //enqueue Unique Work - принимает имя воркера. На каждый воркер с именем свои условия.
                workManager.enqueueUniqueWork(
                    // Какое имя воркера
                    MyWorker.WORK_NAME,
                    // Че делать с воркером, если он уже есть.Replace - воркер заменен. Keep - старый игнор.
                    ExistingWorkPolicy.REPLACE,
                    // Запрос на выполнение работы, в который передаются все параметры и ограничения.
                    // Проще делать в воркере.
                    MyWorker.makeRequest(page++)
                )
            }
        }

















    }



}