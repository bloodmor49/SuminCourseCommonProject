package com.example.morozovhints.l120_async_methods

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.morozovhints.databinding.ActivityMainAsyncBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


//Напоминаемс. Основной поток отвечает за визуализацию - отображение кнопок, экранов и т.д.
//Если в основном потоке запустить загрузку данных либо усыпить его, то вся визуализация зависнет и
//всё крашнется.

//LOOPER - это очередь, бесконечный цикл приема сообщений.
//Handler берет сообщение или задачу и добавляет её в Looper.
//Handler.post - перенаправление выполнения задач в другой поток.
//Использование лупера - мы при создании Handler в конструкторе указываем лупер какого потока нас
//интересует. Например Looper.GetMainLooper() -> мы ссылаемся на очередь главного потока.

//Какие у хэндлера и колбэков проблемы?
//1. CallBack HELL - матрешка.
//2. ГЛАВНЫЙ ПОТОК: Работа с view тОлько из главного потока.
//3. УТЕЧКА ПАМЯТИ: При переворотах экрана уничтожается старая активити, в новой стартует загрузка.
//НО ЗАВЕРШАЕТСЯ ЗАГРУЗКА В ТОЙ АКТИВИТИ, КОТОРАЯ УЖЕ БЫЛА УНИЧТОЖЕНА. Сборщик мусора не собирает их,
//Так как коллбек держит ссылки на уничтоженные активити. Это связано с тем, что у потоков нет жизн.
//цикла. Потоки живут до тех пор, пока не завершат поставленную задачу или работу.

//Для решения этой проблемы в андроиде были придуманы AsyncTask. Но они тоже устарели.
//Для котлина лучший вариант - это корутины.

//Корутины:
//
// 1)lifecycleScope.launch - запускаем Scope - область, где запросы будут делаться. Его жизненный
// цикл аналогичен активити. Т.е. после onDestroy activity сломается и Scope.
// 2) Чтобы главный поток не был заблокирован, пока выполняется запрос, но при этом была задержка
// мы используем suspend - приостановка метода и delay - задержка.
// Иными словами - идет пускаем общую функцию, она приостанавливается и пускает другую функцию,
// которая тоже приостанавливается и пускает третью функцию. А потом все они помирают.
class MainActivityAsync : AppCompatActivity() {

    //viewBinding элементов XML
    private val viewBinding: ActivityMainAsyncBinding by lazy {
        ActivityMainAsyncBinding.inflate(layoutInflater)
    }

    //создаем handler. Устарел он, к сожалению. Причина - при его создании непонятно в каком он потоке.
    //Работая из НЕглавного потока приводит к крашу.
    //т.е. неявное использование может привести к проблемам. Вместо этого правильно указывать лупер,
    //на котором будет работать хейндлер.

    private val handler = Handler()

//    Как обрабатывать сообщения?
//    private val handler = object: Handler() {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            println("HANDLE_MSG $msg")
//        }
//
//    }

    //корутины должны входить в состав объектов с ЖЦ. Т.е. в scope. Мы можем создать свой scope.
    //сorutine context показывает где это происходит.
    //Dispatchers - где выполняются корутины. какой поток.
    // Main - главный поток.
    // IO - поток для чтения и записи.
    // Default - сложные вычисления потоки макс производительности как исходя из числа ядер.

//    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.btnDownload.setOnClickListener {
//            loadDataByMainThread()
//            loadDataByCallback()
//            loadDataByHandler()
//            loadDataByHandlerNew()

            //Корутины: запустили метод внутри Scope. Scope имеет жизн. цикл.
            lifecycleScope.launch {
                loadDataByCourutines()
            }

//            или
//            scope.launch {
//                loadDataByCourutines()
//            }

        }

        //отправка сообщений в хендлер. Редко случается.
//        handler.sendMessage(Message.obtain(handler, 0, 17))
        //
    }

    /////////////Courutines: Как работают корутины?/////////////////////////////////
    private fun loadDataWithoutCourutines(step: Int = 0, obj: Any? = null) {
        //по шагам блоков кода. Похожее поведение, как корутины, но подробно как это работает.
        //Используется state машина с конструкцией when. В функции delay вообще используется postdelay.
        when (step) {
            0 -> {
                viewBinding.progressBarOfDownloading.isVisible = true
                viewBinding.btnDownload.isEnabled = false
                loadCityWithoutCourutines {
                    loadDataWithoutCourutines(step = 1, it)
                }
            }
            1 -> {
                val city = obj as String
                viewBinding.tvCitySet.text = city
                loadWeatherWithoutCourutines(city) {
                    loadDataWithoutCourutines(step = 2, it)
                }

            }
            2 -> {
                val weather = obj as String
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }
        }

    }

    private fun loadCityWithoutCourutines(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            runOnUiThread {
                callback.invoke("Королев")
            }
        }
    }

    private fun loadWeatherWithoutCourutines(city: String, callback: (String) -> Unit) {
        thread {
            runOnUiThread {
                Toast.makeText(this, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            }
            Thread.sleep(5000)
            runOnUiThread {
                callback.invoke("Дождь")
            }
        }
    }

    /////////////Courutines: Корутины - потоки для котлина.//////////////////////////
    private suspend fun loadDataByCourutines() {
        //Три блока - Шаг 1 - выход из блока в city
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        val city = loadCityByCourutines()

        //Три блока - Шаг 2 - выход из блока в weather
        viewBinding.tvCitySet.text = city
        val weather = loadWeatherByCourutines(city)

        //Три блока - Шаг 3
        viewBinding.tvWeatherSet.text = weather
        viewBinding.progressBarOfDownloading.isVisible = false
        viewBinding.btnDownload.isEnabled = true
    }

    //suspend - прерываемая функция. Она должна быть вызвана только из корутины или другой sus функции
    //ОНИ НЕ ДОЛЖНЫ БЛОКИРОВАТЬ ГЛАВНЫЙ ПОТОК.
    private suspend fun loadCityByCourutines(): String {
        //Thread.sleep(5000) -> Это заблокирует поток. ТАК НЕЛЬЗЯ ДЕЛАТЬ.
        delay(5000)
        return "Королев"

    }

    private suspend fun loadWeatherByCourutines(city: String): String {
        Toast.makeText(this, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
        delay(5000)
        return "Дождь"
    }

    /////////////Handler(Looper): Актуальное использование хендлера - нет жизн. цикла/
    private fun loadDataByHandlerNew() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        loadCityByHandlerNew { city: String ->
            viewBinding.tvCitySet.text = city
            loadWeatherByHandlerNew(city) { weather: String ->
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }

        }

    }

    private fun loadCityByHandlerNew(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            //т.е. мы создаем здесь хендлер и отправляем его в очередь runnable методов лупера.
            //со ссылкой на Main Looper, который отвечает за Main Thread
//            Handler(Looper.getMainLooper()).post {
//                callback.invoke("Королев")
//            }
//            вместо вывода выше можео использовать функцию снизу
            runOnUiThread {
                callback.invoke("Королев")
            }
        }

    }

    private fun loadWeatherByHandlerNew(city: String, callback: (String) -> Unit) {
        thread {
            //сначала выкладываем в главный поток тост
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(this, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            }
            //усыпляем этот поток
            Thread.sleep(5000)
            //отправляем в главный поток дождь на вью
            Handler(Looper.getMainLooper()).post {
                callback.invoke("Дождь")
            }

        }
    }

    /////////////Handler:Заработает, устарело ////////////////////////////////////////
    private fun loadDataByHandler() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        loadCityByHandler { city: String ->
            viewBinding.tvCitySet.text = city
            loadWeatherByHandler(city) { weather: String ->
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }

        }

    }

    private fun loadCityByHandler(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            //добавить задачу в очередь данного потока.
            //так как обработчик создан в главном потоке, то и метод run будет в нем вызван
            handler.post {
                callback.invoke("Королев")
            }
        }

    }

    private fun loadWeatherByHandler(city: String, callback: (String) -> Unit) {
        thread {
            //сначала выкладываем в главный поток тост
            handler.post {
                Toast.makeText(this, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            }
            //усыпляем этот поток
            Thread.sleep(5000)
            //отправляем в главный поток дождь на вью
            handler.post {
                callback.invoke("Дождь")
            }

        }
    }

    /////////////Callback:Не заработают потоки внутри матрешки (view из main Thread)//
    private fun loadDataByCallback() {
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        //стартуем загрузку... и начинается callback HELL  - матрешка из callback, так как
        //при выполнении одного callback - его результат сразу перетекает в другой, тем самым
        //стартуя ещё раз.
        loadCityByCallback { city: String ->
            viewBinding.tvCitySet.text = city
            //по завершению снова начинаем загрузку
            //во проблема в том, что вью можно запустить только из главного потока. ПОэтому
            //матрешка приведет к крашу.
            loadWeatherByCallback(city) { weather: String ->
                viewBinding.tvWeatherSet.text = weather
                viewBinding.progressBarOfDownloading.isVisible = false
                viewBinding.btnDownload.isEnabled = true
            }

        }

    }

    private fun loadCityByCallback(callback: (String) -> Unit) {
        //Создаем новый поток
        //Это асинхронный код.
        thread {
            Thread.sleep(5000)
            //так как это другой поток, то нельзя просто взять и вернуть строку
            //поэтому используем callback
            callback.invoke("Королев")
            //
        }

    }

    private fun loadWeatherByCallback(city: String, callback: (String) -> Unit) {
        thread {
            Toast.makeText(this, "Загрузка погоды...$city", Toast.LENGTH_LONG).show()
            Thread.sleep(5000)
            callback.invoke("Дождь")
        }
    }

    /////////////НЕ ЗАРАБОТАЕТ - ЗАГРУЗКА С ГЛАВНОГО ПОТОКА///////////////////////////
    private fun loadDataByMainThread() {
        //Это синхронный код
        viewBinding.progressBarOfDownloading.isVisible = true
        viewBinding.btnDownload.isEnabled = false
        val city = loadCityByMainThread()
        viewBinding.tvCitySet.text = city
        val weather = loadWeatherByMainThread(city)
        viewBinding.tvWeatherSet.text = weather
        viewBinding.progressBarOfDownloading.isVisible = false
        viewBinding.btnDownload.isEnabled = true
    }

    private fun loadCityByMainThread(): String {
        Thread.sleep(5000)
        return "Королев"
    }

    private fun loadWeatherByMainThread(city: String): String {
        Toast.makeText(this, "Загрузка погоды... $city", Toast.LENGTH_LONG).show()
        Thread.sleep(5000)
        return "Дождь"
    }
}