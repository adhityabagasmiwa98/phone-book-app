package com.adhityabagasmiwa.phonebook.infrastructures.di.modules

import android.util.Log
import com.adhityabagasmiwa.data.payload.api.services.AuthApi
import com.adhityabagasmiwa.data.persistences.mappers.AuthDataMapper
import com.adhityabagasmiwa.data.persistences.mappers.AuthDataMapperImpl
import com.adhityabagasmiwa.data.persistences.repositories.AuthRepositoryImpl
import com.adhityabagasmiwa.data.persistences.repositories.AuthRepositoryInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataModule = module {

    /*factory {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }*/

    /*factory<Retrofit> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://phone-book-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(client)
            .build()
    }*/

    /*factory<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }*/

    // OkHttpClient
    /*single {
        OkHttpClient().newBuilder().apply {
            retryOnConnectionFailure(true)

            connectTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            callTimeout(1, TimeUnit.MINUTES)

            addInterceptor {
                return@addInterceptor it.proceed(it.request().newBuilder().apply {
                    addHeader("Content-Type", "application/json;charset=UTF-8")
                    addHeader("Accept", "application/json;charset=UTF-8")
                }.build())
            }

            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor { message ->
                    Log.d("API-LOG", message)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()
    }*/

    factory {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .create()
    }

    factory<Retrofit> {
        val interceptor = HttpLoggingInterceptor { message: String -> Log.d("API", message) }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://phone-book-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(client)
            .build()
    }

    /*single<Gson> {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }*/

    /*single<Retrofit> {
        Retrofit.Builder().apply {
            baseUrl("https://phone-book-api.herokuapp.com/api/")
            client(get<OkHttpClient>())
            addConverterFactory(GsonConverterFactory.create(get<Gson>()))
        }.build()
    }*/

    // service
    factory<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }

    // mapper
    factory<AuthDataMapper> { AuthDataMapperImpl() }

    // repository
    factory<AuthRepositoryInterface> { AuthRepositoryImpl(get(), get()) }

}