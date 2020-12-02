package com.example.moonapp

import android.app.Application
import com.example.moonapp.di.apiModule
import com.example.moonapp.di.baseApiModule
import com.example.moonapp.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MoonApp)
            modules(baseApiModule, apiModule, vmModule)
        }
    }
}