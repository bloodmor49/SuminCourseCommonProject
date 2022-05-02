package com.example.morozovhints.l140_dagger2.ex2_dagger2.data.datasource

/**
 * У интерфейса нет конструкторов, поэтому невозможен inject.
 * Но можно решить проблему через binding (т.е. мы биндим вызов репозитория с его реализацией для даггера)
 * Это делается в компоненте. (А точнее - модулях компонента)
 */
interface ExampleLocalDataSource {
    fun method()
}