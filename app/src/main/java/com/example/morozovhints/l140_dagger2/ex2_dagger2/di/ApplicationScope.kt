package com.example.morozovhints.l140_dagger2.ex2_dagger2.di

import javax.inject.Scope

/**
 * Создаем свой scope для синглтона.
 * Чтобы самим решать когда синглтон должен быть пересоздан.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope {
}