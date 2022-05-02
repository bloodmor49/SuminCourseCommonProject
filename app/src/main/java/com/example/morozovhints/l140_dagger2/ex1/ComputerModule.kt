package com.example.morozovhints.l140_dagger2.ex1

import dagger.Module
import dagger.Provides


/**
 * Класс модуль даггера.
 * Используется, если нельзя повестить аннотацию на класс @Inject constructor()
 * Если даггер не находит, то ищет провайдер. Также в компоненте нужно перечислить все модули.
 */
@Module
class ComputerModule {

    @Provides
    fun provideMonitor(): Monitor {
        return Monitor()
    }

    @Provides
    fun provideKeyboard(): Keyboard {
        return Keyboard()
    }

    @Provides
    fun provideMouse(): Mouse {
        return Mouse()
    }

    @Provides
    fun provideStorage(): Storage {
        return Storage()
    }

    @Provides
    fun provideMemory(): Memory {
        return Memory()
    }

    @Provides
    fun provideProcessor(): Processor {
        return Processor()
    }


    @Provides
    fun provideComputerTower(
        storage: Storage,
        memory: Memory,
        processor: Processor,
    ): ComputerTower {
        return ComputerTower(storage, memory, processor)
    }


}