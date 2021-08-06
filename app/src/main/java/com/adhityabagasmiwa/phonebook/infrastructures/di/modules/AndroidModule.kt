package com.adhityabagasmiwa.phonebook.infrastructures.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.adhityabagasmiwa.phonebook.BaseApplication
import com.adhityabagasmiwa.phonebook.sessions.LocalSession
import com.adhityabagasmiwa.phonebook.ui.presenters.MainPresenter
import com.adhityabagasmiwa.phonebook.ui.viewmodels.AuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    factory { BaseApplication.getInstance().applicationContext }

    /*single<CoroutineContext> { Executors.newSingleThreadExecutor().asCoroutineDispatcher() }*/

    // sharedPreferences
    factory<SharedPreferences> {
        get<Context>().getSharedPreferences(
            "id.adhityabagasmiwa.sharedPreferences",
            Context.MODE_PRIVATE
        )
    }

    // local session
    factory { LocalSession(get()) }

    // viewModel
    viewModel { AuthViewModel(get(), get()) }

    // presenter
    factory { MainPresenter(get(), get()) }
}
