package com.adhityabagasmiwa.phonebook.infrastructures.di.modules

import com.adhityabagasmiwa.usescases.cases.AuthUseCaseImpl
import com.adhityabagasmiwa.usescases.infrastructures.AuthUseCaseListener
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {

//    factory(named("APIUserRegisterUseCase")) { AuthUseCase(get(named("APIUserRegisterRepository")), get()) }

    single<AuthUseCaseListener> { AuthUseCaseImpl(get()) }

//    single(named("AuthUseCase")) { AuthUseCaseImpl(get()) }
}