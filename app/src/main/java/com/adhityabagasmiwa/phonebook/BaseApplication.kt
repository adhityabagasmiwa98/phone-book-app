package com.adhityabagasmiwa.phonebook

import android.app.Application
import com.adhityabagasmiwa.phonebook.infrastructures.di.containers.AppContainer
import com.adhityabagasmiwa.phonebook.infrastructures.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this

        AppContainer.loadModules()
    }

    companion object {
        lateinit var appInstance: BaseApplication
        fun getInstance(): BaseApplication {
            return appInstance
        }
    }

}