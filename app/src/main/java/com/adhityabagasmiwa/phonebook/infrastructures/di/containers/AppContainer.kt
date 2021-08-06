package com.adhityabagasmiwa.phonebook.infrastructures.di.containers

import com.adhityabagasmiwa.phonebook.infrastructures.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object AppContainer {
    fun loadModules() {
        startKoin {
            modules(
                androidModule,
                dataModule,
                useCaseModule
            )
        }
    }
}