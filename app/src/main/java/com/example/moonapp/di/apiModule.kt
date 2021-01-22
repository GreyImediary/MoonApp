package com.example.moonapp.di

import com.example.moonapp.api.MoonService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * DI на Koin для создания имплементации MoonService
 * @see [Koin]](https://doc.insert-koin.io/#/introduction)
 * */

object RetrofitProperties {
    const val BASE_URL = "BASE_URL"
}

private fun getBaseUrl() =
    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/weatherdata/"

private fun getGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

private inline fun <reified T> createApi(
    baseUrl: String,
    gsonConverterFactory: GsonConverterFactory
) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(gsonConverterFactory)
    .build().create(T::class.java)

val baseApiModule = module {
    single(named(RetrofitProperties.BASE_URL)) { getBaseUrl() }
    single { getGsonConverter() }
}

val apiModule = module {
    single {
        createApi<MoonService>(
            get(named(RetrofitProperties.BASE_URL)),
            get()
        )
    }
}