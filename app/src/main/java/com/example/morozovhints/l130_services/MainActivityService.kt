package com.example.morozovhints.l130_services

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.morozovhints.databinding.ActivityMainServiceBinding
import java.util.*


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

        /////////////////////// КЛАССИЧЕСКИЙ СЕРВИС ///////////////////////////

        viewbinding.simpleService.setOnClickListener {
            Toast.makeText(this, "Click", Toast.LENGTH_LONG).show()
            startService(MyService.newIntent(this, 25))
        }
        viewbinding.simpleService.setOnLongClickListener {
            stopService(MyService.newIntent(this, 0))
        }

        /////////////////////// FOREGROUND SERVICE ///////////////////////////

        viewbinding.foregroundService.setOnClickListener {
            ContextCompat.startForegroundService(this, MyForeGroundService.newIntent(this))
        }
        viewbinding.foregroundService.setOnLongClickListener {
            Toast.makeText(this, "Stop service", Toast.LENGTH_LONG).show()
            stopService(MyForeGroundService.newIntent(this))
        }

        /////////////////////// INTENT SERVICE ///////////////////////////

        viewbinding.intentService.setOnClickListener {
            ContextCompat.startForegroundService(this, MyIntentService.newIntent(this))
        }

        /////////////////////// JOB SERVICE ///////////////////////////

        viewbinding.jobScheduler.setOnClickListener {
            val componentName = ComponentName(this, MyJobService::class.java)
            val jobInfo = JobInfo.Builder(MyJobService.JOB_ID, componentName)
//                .setExtras(MyJobService.newBundle(page++))
                .setRequiresCharging(true)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setPersisted(true)
//                .setPeriodic()
                .build()
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intent = MyJobService.newIntent(page++)
                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
            } else {
                val intent = MyIntentServiceForJobLower26.newIntent(this, page++)
                startService(intent)
            }

            /////////////////////// JOB INTENT SERVICE ///////////////////////////

            viewbinding.jobIntentService.setOnClickListener {
                MyJobIntentService.enqueue(this, page++)
            }

            /////////////////////// WORKMANAGER SERVICE ///////////////////////////

            viewbinding.workManager.setOnClickListener {
                val workManager = WorkManager.getInstance()
                workManager.enqueueUniqueWork(
                    MyWorker.WORK_NAME,
                    ExistingWorkPolicy.REPLACE,
                    MyWorker.makeRequest(page++)
                )
            }

            /////////////////////// ALARM MANAGER ///////////////////////////

            viewbinding.alarmManager.setOnClickListener {
                //Создаем аларм менеджер
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                //Создаем календарь.
                val calendar = Calendar.getInstance()
                //Добавляем в него выполнение задачи через 30 сек.
                calendar.add(Calendar.SECOND, 30)
                //Создаем намерение.
                val intent = MyAlarmReceiver.newIntent(this)
                //Создаем обертку над интендом для получения вещания.
                val pendingIntent = PendingIntent.getBroadcast(this, 100, intent, 0)
                //Запускаем сервис. В нем указывае работу, время, операцию после срабатывания.
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
//                alarmManager.cancel(pendingIntent)
            }
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = (service as? MyForeGroundService.LocalBinder) ?: return
            val foregroundService = binder.getService()
            foregroundService.onProgressChanged = { progress ->
                viewbinding.progressBarLoading.progress = progress
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("ServiceForeground", "$name disconnected")
        }
    }

    override fun onStart() {
        super.onStart()

//      Подписываемся на наш Foreground Service.
        bindService(
            MyForeGroundService.newIntent(this),
            serviceConnection,
            0
        )
    }

    override fun onStop() {
        super.onStop()

//      отписываемся от сервиса.
        unbindService(serviceConnection)
    }
}