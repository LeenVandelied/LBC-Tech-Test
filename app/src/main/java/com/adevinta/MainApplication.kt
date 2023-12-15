package com.adevinta

import android.app.Application
import com.adevinta.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        // start Koin!
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}